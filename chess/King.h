#ifndef KING_H_
#define KING_H_

#include <iostream>
#include "Piece.h"

/**
 * @class King
 * @brief Represents a king chess piece
 *
 * Implements king-specific movement rules and properties.
 */
class King : public Piece {
    public:
    /**
     * @brief Constructs a new king
     *
     * @param color Piece color (black/white)
     * @param location Initial square
     */
    explicit King(Piece::Color color, Square& location) : Piece(color, location) { }

    /**
     * @brief Gets the king's value (200)
     *
     * @return Constant value 200
     */
    piece_value_t value() const override;

    /**
     * @brief Checks if king can move to a square
     *
     * @param location Destination square
     * @return True if move is valid, false otherwise
     */
    bool can_move_to(const Square& location) const override;

    /**
     * @brief Gets the king's string representation
     *
     * @return "K" for black, "k" for white
     */
    std::string str() const override;
};

#endif // KING_H_
