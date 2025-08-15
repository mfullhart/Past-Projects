#include "Bishop.h"
#include "Square.h"
#include <cmath>

using namespace std;

piece_value_t Bishop::value() const {
    return 3;
}

bool Bishop::can_move_to(const Square& location) const {
    Square* current = this->location();
    if (!current) return false;

    int rank_diff = abs(static_cast<int>(current->rank()) - static_cast<int>(location.rank()));
    int file_diff = abs(static_cast<int>(current->file()) - static_cast<int>(location.file()));

    return rank_diff == file_diff && rank_diff != 0;
}

string Bishop::str() const {
    return color() == Color::white ? "b" : "B";
}