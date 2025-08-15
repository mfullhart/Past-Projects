#ifndef ROOK_H_
#define ROOK_H_

#include <iostream>
#include "Piece.h"

/**
 * @class Rook
 * @brief Represents a rook chess piece
 *
 * Implements rook-specific movement rules and properties.
 */
class Rook : public Piece {
  public:
    /**
     * @brief Constructs a new rook
     *
     * @param color Piece color (black/white)
     * @param location Initial square
     */
    Rook(Piece::Color color, Square& location) : Piece(color, location) { }

    /**
     * @brief Gets the rook's value (5)
     *
     * @return Constant value 5
     */
    piece_value_t value() const override;

    /**
     * @brief Checks if rook can move to a square
     *
     * @param location Destination square
     * @return True if move is valid, false otherwise
     */
    bool can_move_to(const Square& location) const override;

    /**
     * @brief Gets the rook's string representation
     *
     * @return "R" for black, "r" for white
     */
    std::string str() const override;
};

#endif // ROOK_H_
