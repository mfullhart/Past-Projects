#include "Pawn.h"
#include "Square.h"
#include <cmath>

using namespace std;

Pawn::Pawn(Piece::Color color, Square& location)
    : Piece(color, location), _moved(false) {}

piece_value_t Pawn::value() const { return 1; }

bool Pawn::is_valid_capture(const Square& location) const {
    Square* current = this->location();
    if (!current) return false;

    int rank_diff = static_cast<int>(location.rank()) - static_cast<int>(current->rank());
    int file_diff = abs(static_cast<int>(current->file()) - static_cast<int>(location.file()));

    if (color() == Color::white) {
        return (rank_diff == -1 && file_diff == 1);
    } else {
        return (rank_diff == 1 && file_diff == 1);
    }
}

bool Pawn::can_move_to(const Square& location) const {
    Square* current = this->location();
    if (!current) return false;

    if (current->rank() == location.rank() && current->file() == location.file()) {
        return false;
    }

    int rank_diff = static_cast<int>(location.rank()) - static_cast<int>(current->rank());
    int file_diff = abs(static_cast<int>(current->file()) - static_cast<int>(location.file()));

    if (file_diff == 1) {
        return is_valid_capture(location) && location.is_occupied();
    }

    if (file_diff == 0) {
        if (location.is_occupied()) return false;
        if (color() == Color::white) {
            return (rank_diff == -1) || (!_moved && rank_diff == -2);
        } else {
            return (rank_diff == 1) || (!_moved && rank_diff == 2);
        }
    }

    return false;
}

bool Pawn::move_to(Square& location) {
    if (can_move_to(location)) {
        bool success = Piece::move_to(location);
        if (success) _moved = true;
        return success;
    }
    return false;
}

std::string Pawn::str() const {
    return color() == Color::white ? "p" : "P";
}

// Added implementation
bool Pawn::has_moved() const {
    return _moved;
}