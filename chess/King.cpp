#include "King.h"
#include "Square.h"
#include <cmath>

using namespace std;

piece_value_t King::value() const {
    return 200;
}

bool King::can_move_to(const Square& location) const {
    Square* current = this->location();
    if (!current) return false;

    int rank_diff = abs(static_cast<int>(current->rank()) - static_cast<int>(location.rank()));
    int file_diff = abs(static_cast<int>(current->file()) - static_cast<int>(location.file()));

    return (rank_diff <= 1 && file_diff <= 1) && (rank_diff != 0 || file_diff != 0);
}

string King::str() const {
    return color() == Color::white ? "k" : "K";
}
