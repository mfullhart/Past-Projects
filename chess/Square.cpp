#include "Square.h"
#include "Piece.h"
#include <iostream>

using namespace std;

size_t Square::rank() const {
    return _rank;
}

size_t Square::file() const {
    return _file;
}

bool Square::is_occupied() const {
    return _occupant != nullptr;
}

Piece* Square::occupant() const {
    return _occupant;
}

void Square::set_occupant(Piece* occupant) {
    _occupant = occupant;
}

ostream& operator<<(ostream& os, const Square& square) {
    if (square.is_occupied()) {
        os << *square.occupant();
    } else {
        os << " ";
    }
    return os;
}
