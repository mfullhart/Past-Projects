#include "Board.h"
#include "Piece.h"

#include <stdexcept>
#include <cctype>

using namespace std;

Board::Board() {
    // Initialize all squares on the board
    for (size_t rank = 0; rank < SIZE; rank++) {
        for (size_t file = 0; file < SIZE; file++) {
            _squares[rank][file] = new Square(rank, file);
        }
    }
}

Square& Board::square_at(size_t rank, size_t file) const {
    if (rank >= SIZE || file >= SIZE) {
        throw out_of_range("Invalid rank or file index");
    }
    return *_squares[rank][file];
}

Square& Board::square_at(const string& identifier) const {
    if (identifier.length() != 2) {
        throw invalid_argument("Square identifier must be 2 characters");
    }

    char file_char = tolower(identifier[0]);
    char rank_char = identifier[1];

    // Convert file to 0-7 (a-h) and rank to 0-7 (1-8)
    size_t file = file_char - 'a';
    size_t rank = SIZE - (rank_char - '0');

    if (file >= SIZE || rank >= SIZE) {
        throw out_of_range("Invalid square identifier");
    }

    return square_at(rank, file);
}

bool Board::is_valid_rank(const Square& from, const Square& to) const {
    return from.rank() == to.rank();
}

bool Board::is_valid_file(const Square& from, const Square& to) const {
    return from.file() == to.file();
}

bool Board::is_valid_diag(const Square& from, const Square& to) const {
    int rank_diff = abs(static_cast<int>(from.rank()) - static_cast<int>(to.rank()));
    int file_diff = abs(static_cast<int>(from.file()) - static_cast<int>(to.file()));
    return rank_diff == file_diff && rank_diff != 0;
}

bool Board::is_clear_rank(const Square& from, const Square& to) const {
    // Verify squares are on the same rank
    if (!is_valid_rank(from, to)) return false;

    // Determine the leftmost and rightmost files
    size_t start_file = min(from.file(), to.file());
    size_t end_file = max(from.file(), to.file());

    // Check each square between them (excluding start and end)
    for (size_t file = start_file + 1; file < end_file; file++) {
        if (square_at(from.rank(), file).is_occupied()) {
            return false;
        }
    }
    return true;
}

bool Board::is_clear_file(const Square& from, const Square& to) const {
    if (!is_valid_file(from, to)) return false;

    size_t start_rank = std::min(from.rank(), to.rank());
    size_t end_rank = std::max(from.rank(), to.rank());

    for (size_t rank = start_rank + 1; rank < end_rank; rank++) {
        if (square_at(rank, from.file()).is_occupied()) {
            return false;
        }
    }
    return true;
}

bool Board::is_clear_diag(const Square& from, const Square& to) const {
    // Verify this is a valid diagonal
    if (!is_valid_diag(from, to)) return false;

    // Determine direction of movement (1 = increasing, -1 = decreasing)
    int rank_step = (to.rank() > from.rank()) ? 1 : -1;
    int file_step = (to.file() > from.file()) ? 1 : -1;

    // Start checking from the next square along the path
    size_t rank = from.rank() + rank_step;
    size_t file = from.file() + file_step;

    // Walk the diagonal until we reach the destination
    while (rank != to.rank() && file != to.file()) {
        if (square_at(rank, file).is_occupied()) {
            return false;
        }
        rank += rank_step;
        file += file_step;
    }
    return true;
}

Board::~Board() {
    // Clean up all allocated squares
    for (size_t rank = 0; rank < SIZE; rank++) {
        for (size_t file = 0; file < SIZE; file++) {
            delete _squares[rank][file];
        }
    }
}

std::ostream& operator<<(std::ostream& os, const Board& board) {
    os << "   a   b   c   d   e   f   g   h\n";

    for (size_t rank = 0; rank < Board::SIZE; rank++) {
        os << " +---+---+---+---+---+---+---+---+\n";
        os << (Board::SIZE - rank) << "|";

        for (size_t file = 0; file < Board::SIZE; file++) {
            os << " " << board.square_at(rank, file) << " |";
        }

        os << (Board::SIZE - rank) << "\n";
    }

    os << " +---+---+---+---+---+---+---+---+\n";
    os << "   a   b   c   d   e   f   g   h\n";

    return os;
}