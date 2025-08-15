#include "Queen.h"
#include "Square.h"
#include <cmath>

using namespace std;

piece_value_t Queen::value() const {
    return 9;
}

bool Queen::can_move_to(const Square& location) const {
    Square* current = this->location();
    if (!current) return false;

    int rank_diff = abs(static_cast<int>(current->rank()) - static_cast<int>(location.rank()));
    int file_diff = abs(static_cast<int>(current->file()) - static_cast<int>(location.file()));

    return (current->rank() == location.rank() ||
            current->file() == location.file() ||
            rank_diff == file_diff);
}

string Queen::str() const {
    return color() == Color::white ? "q" : "Q";
}
