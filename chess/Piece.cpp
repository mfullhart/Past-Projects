#include "Piece.h"
#include "Square.h"
#include <iostream>

using namespace std;

Piece::Color Piece::color() const {
    return _color;
}

Square* Piece::location() const {
    return _location;
}

void Piece::set_location(Square* location) {
    if (_location) {
        _location->set_occupant(nullptr);
    }

    _location = location;

    if (location) {
        location->set_occupant(this);
    }
}

bool Piece::is_on_square() const {
    return _location != nullptr;
}

bool Piece::move_to(Square& location) {
    if (can_move_to(location)) {
        set_location(&location);
        return true;
    }
    return false;
}

void Piece::capture() {
    set_location(nullptr);
}

ostream& operator<<(ostream& os, const Piece& piece) {
    os << piece.str();
    return os;
}
