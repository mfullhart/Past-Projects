#ifndef BISHOP_H_
#define BISHOP_H_

#include <iostream>
#include "Piece.h"

/**
 * @class Bishop
 * @brief Represents a bishop chess piece
 *
 * Implements bishop-specific movement rules and properties.
 */
class Bishop : public Piece {
    public:

    /**
     * @brief Constructs a new bishop
     *
     * @param color Piece color (black/white)
     * @param location Initial square
     */
    Bishop(Piece::Color color, Square& location) : Piece(color, location) { }

    /**
     * @brief Gets the bishop's value (3)
     *
     * @return Constant value 3
     */
    piece_value_t value() const override;

    /**
     * @brief Checks if bishop can move to a square
     *
     * @param location Destination square
     * @return True if move is valid, false otherwise
     */
    bool can_move_to(const Square& location) const override;

    /**
     * @brief Gets the bishop's string representation
     *
     * @return "B" for black, "b" for white
     */
    std::string str() const override;
};

#endif // BISHOP_H_
