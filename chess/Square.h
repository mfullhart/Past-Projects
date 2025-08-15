#ifndef SQUARE_H_
#define SQUARE_H_

#include <iostream>
#include "Piece.h"

/**
 * @class Square
 * @brief Represents a single square on the chess board
 *
 * Each square knows its position and can hold one chess piece.
 */
class Square {
  public:
  /**
     * @brief Constructs a new square
     *
     * @param rank The row (0-7)
     * @param file The column (0-7)
     */
    Square(size_t rank, size_t file) : _rank(rank), _file(file) { }

    /**
     * @brief Gets the square's rank
     *
     * @return Rank index (0-7)
     */
    size_t rank() const;

    /**
     * @brief Gets the square's file
     *
     * @return File index (0-7)
     */
    size_t file() const;

    /**
     * @brief Checks if square is occupied
     *
     * @return True if occupied, false otherwise
     */
    bool is_occupied() const;

    /**
     * @brief Gets the occupying piece
     *
     * @return Pointer to piece (nullptr if empty)
     */
    Piece* occupant() const;

  private:
    /**
     * @brief Sets the occupying piece
     *
     * @param occupant Piece to place on square
     */
    void set_occupant(Piece* occupant);

    const size_t _rank; // Row position (0-7)
    const size_t _file;  // Column position (0-7)
    Piece* _occupant = nullptr; // Current occupying piece

    friend void Piece::set_location(Square* location);
    friend void Piece::capture();
};

/**
 * @brief Output operator for squares
 *
 * @param os Output stream
 * @param square Square to output
 * @return Reference to the output stream
 *
 * Outputs the occupant's string rep or space if empty
 */
std::ostream& operator<<(std::ostream& os, const Square& square);

#endif // SQUARE_H_
