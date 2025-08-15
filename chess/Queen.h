#ifndef QUEEN_H_
#define QUEEN_H_

#include <iostream>
#include "Piece.h"

/**
 * @class Queen
 * @brief Represents a queen chess piece
 *
 * Implements queen-specific movement rules and properties.
 * Combines the movement capabilities of rook and bishop.
 */
class Queen : public Piece {
  public:
    /**
     * @brief Constructs a new queen
     *
     * @param color Piece color (black/white)
     * @param location Initial square
     */
    Queen(Piece::Color color, Square& location) : Piece(color, location) { }

    /**
     * @brief Gets the queen's value (9)
     *
     * @return Constant value 9
     */
    piece_value_t value() const override;

    /**
     * @brief Checks if queen can move to a square
     *
     * @param location Destination square
     * @return True if move is valid, false otherwise
     */
    bool can_move_to(const Square& location) const override;

    /**
     * @brief Gets the queen's string representation
     *
     * @return "Q" for black, "q" for white
     */
    std::string str() const override;
};

#endif // QUEEN_H_
