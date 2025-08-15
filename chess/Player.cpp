#include "Player.h"
#include "Pawn.h"
#include "Rook.h"
#include "Knight.h"
#include "Bishop.h"
#include "Queen.h"
#include "King.h"

Player::Player(Piece::Color color, const Board& board)
    : _color(color), _board(board), _king(nullptr) {

    // Initialize pawns
    int pawn_rank = (_color == Piece::Color::white) ? 1 : 6;
    for (char file = 'a'; file <= 'h'; ++file) {
        Square& square = _board.square_at(std::string(1, file) + std::to_string
            (pawn_rank + 1));
        Piece* pawn = new Pawn(_color, square);
        _pieces.push_back(pawn);
        pawn->set_location(&square);
    }

    // Initialize back rank pieces
    int back_rank = (_color == Piece::Color::white) ? 0 : 7;

    // Rooks
    Piece* rook1 = new Rook(_color, _board.square_at("a" + std::to_string
        (back_rank + 1)));
    _pieces.push_back(rook1);
    rook1->set_location(&_board.square_at("a" + std::to_string(back_rank + 1)));

    Piece* rook2 = new Rook(_color, _board.square_at("h" + std::to_string
        (back_rank + 1)));
    _pieces.push_back(rook2);
    rook2->set_location(&_board.square_at("h" + std::to_string(back_rank + 1)));

    // Knights
    Piece* knight1 = new Knight(_color, _board.square_at("b" + std::to_string
        (back_rank + 1)));
    _pieces.push_back(knight1);
    knight1->set_location(&_board.square_at("b" + std::to_string(back_rank + 1)));

    Piece* knight2 = new Knight(_color, _board.square_at("g" + std::to_string
        (back_rank + 1)));
    _pieces.push_back(knight2);
    knight2->set_location(&_board.square_at("g" + std::to_string(back_rank + 1)));

    // Bishops
    Piece* bishop1 = new Bishop(_color, _board.square_at("c" + std::to_string
        (back_rank + 1)));
    _pieces.push_back(bishop1);
    bishop1->set_location(&_board.square_at("c" + std::to_string(back_rank + 1)));

    Piece* bishop2 = new Bishop(_color, _board.square_at("f" + std::to_string
        (back_rank + 1)));
    _pieces.push_back(bishop2);
    bishop2->set_location(&_board.square_at("f" + std::to_string(back_rank + 1)));

    // Queen
    Piece* queen = new Queen(_color, _board.square_at("d" + std::to_string
        (back_rank + 1)));
    _pieces.push_back(queen);
    queen->set_location(&_board.square_at("d" + std::to_string(back_rank + 1)));

    // King
    _king = new King(_color, _board.square_at("e" + std::to_string
        (back_rank + 1)));
    _pieces.push_back(_king);
    _king->set_location(&_board.square_at("e" + std::to_string(back_rank + 1)));
}

Piece::Color Player::color() const {
    return _color;
}

bool Player::make_move(const std::string& from, const std::string& to) {
    try {
        Square& from_square = _board.square_at(from);
        Square& to_square = _board.square_at(to);

        Piece* piece = from_square.occupant();
        if (!piece || piece->color() != _color) {
            return false;
        }

        Piece* target = to_square.occupant();
        if (target && target->color() == _color) {
            return false;
        }

        if (!piece->can_move_to(to_square)) {
            return false;
        }

        std::string piece_str = piece->str();
        if (piece_str != "n" && piece_str != "N") {
            if (_board.is_valid_rank(from_square, to_square) && !_board.is_clear_rank(from_square,
                to_square)) {
                return false;
            }
            if (_board.is_valid_file(from_square, to_square) && !_board.is_clear_file(from_square,
                to_square)) {
                return false;
            }
            if (_board.is_valid_diag(from_square, to_square) && !_board.is_clear_diag(from_square,
                to_square)) {
                return false;
            }
        }

        return piece->move_to(to_square);
    } catch (...) {
        return false;
    }

}

bool Player::is_in_check(const Board& board) const {
    if (!_king || !_king->location()) return false;

    // Check if any opponent's piece can attack the king
    for (Piece* piece : _opponent->_pieces) {
        if (piece->location() &&
            piece->can_move_to(*_king->location()) &&
            board.is_path_clear(*piece->location(), *_king->location())) {
            return true;
        }
    }
    return false;
}

bool Player::is_move_safe(const Square& from, const Square& to, Board& board) const {
    // Simulate the move
    Piece* movingPiece = from.occupant();
    Piece* capturedPiece = to.occupant();

    // Make the move temporarily
    movingPiece->set_location(const_cast<Square*>(&to));
    bool inCheck = is_in_check(board);

    // Undo the move
    movingPiece->set_location(const_cast<Square*>(&from));
    if (capturedPiece) {
        capturedPiece->set_location(const_cast<Square*>(&to));
    }

    return !inCheck;
}

bool Player::is_in_checkmate(const Board& board) {
    if (!is_in_check(board)) return false;

    // Check if any valid move exists that gets out of check
    for (Piece* piece : _pieces) {
        if (!piece->location()) continue;

        Square* current = piece->location();
        for (size_t rank = 0; rank < Board::SIZE; ++rank) {
            for (size_t file = 0; file < Board::SIZE; ++file) {
                Square& target = board.square_at(rank, file);
                if (piece->can_move_to(target) &&
                    is_move_safe(*current, target, board)) {
                    return false; // Found at least one valid move
                }
            }
        }
    }
    return true;
}

std::vector<std::string> Player::get_all_valid_moves(const Board& board) const {
    std::vector<std::string> moves;

    for (Piece* piece : _pieces) {
        if (!piece->location()) continue;

        std::string from = piece->location()->algebraic_name();
        for (size_t rank = 0; rank < Board::SIZE; ++rank) {
            for (size_t file = 0; file < Board::SIZE; ++file) {
                Square& target = board.square_at(rank, file);
                if (piece->can_move_to(target) &&
                    is_move_safe(*piece->location(), target, board)) {
                    moves.push_back(from + target.algebraic_name());
                }
            }
        }
    }

    return moves;
}

piece_value_t Player::piece_value() const {
    piece_value_t total = 0;
    for (const Piece* piece : _pieces) {
        if (piece->location() != nullptr) {
            total += piece->value();
        }
    }
    return total;
}

Player::~Player() {
    for (Piece* piece : _pieces) {
        delete piece;
    }
}