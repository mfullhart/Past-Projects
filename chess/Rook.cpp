#include "Rook.h"
#include "Square.h"
#include <cmath>

using namespace std;

piece_value_t Rook::value() const {
    return 5;
}

bool Rook::can_move_to(const Square& location) const {
    Square* current = this->location();
    if (!current) return false;

    return (current->rank() == location.rank() ||
            current->file() == location.file()) &&
           !(current->rank() == location.rank() &&
             current->file() == location.file());
}

string Rook::str() const {
    return color() == Color::white ? "r" : "R";
}
