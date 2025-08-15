#ifndef KNIGHT_H_
#define KNIGHT_H_

#include <iostream>
#include "Piece.h"

/**
 * @class Knight
 * @brief Represents a knight chess piece
 *
 * Implements knight-specific movement rules and properties.
 */
class Knight : public Piece {
    public:
    /**
     * @brief Constructs a new knight
     *
     * @param color Piece color (black/white)
     * @param location Initial square
     */
    Knight(Piece::Color color, Square& location) : Piece(color, location) { }

    /**
     * @brief Gets the knight's value (3)
     *
     * @return Constant value 3
     */
    piece_value_t value() const override;

    /**
     * @brief Checks if knight can move to a square
     *
     * @param location Destination square
     * @return True if move is valid, false otherwise
     */
    bool can_move_to(const Square& location) const override;

    /**
     * @brief Gets the knight's string representation
     *
     * @return "N" for black, "n" for white
     */
    std::string str() const override;
};

#endif // KNIGHT_H_
