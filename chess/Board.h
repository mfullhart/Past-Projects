#ifndef BOARD_H_
#define BOARD_H_

#include <iostream>
#include "Square.h"

class Board {
  public:
    static const size_t SIZE = 8;

    /**
     * @breif Constructs a 8x8 chess board
     *
     * Initializes all 64 squares on the board with their ranks and file positions.
     */
    Board();

    /**
     * @brief Gets a square reference by rank and file indices
     *
     * @param rank The rank(row) index
     * @param file The file (column) index
     * @return Square& Reference to the requested square
     * @throw out_of_range If indices are out of bounds
     */
    Square& square_at(size_t rank, size_t file) const;

    /**
     * @brief Gets a square reference by algebraic notation
     *
     * @param identifier The algebraic notation string (e.g., "e4")
     * @return Square& Reference to the requested square
     * @throw invalid_argument If identifier is malformed
     * @throw out_of_range If position is invalid
     */
    Square& square_at(const std::string& identifier) const;

    /**
     * @brief Checks if two squares share the same rank
     *
     * @param from Starting square
     * @param to Destination square
     * @return true If squares are on the same rank
     */
    bool is_valid_rank(const Square& from, const Square& to) const;

    /**
     * @brief Checks if two squares share the same file
     *
     * @param from Starting square
     * @param to Destination square
     * @return If squares are on the same file
     */
    bool is_valid_file(const Square& from, const Square& to) const;

    /**
     * @brief Checks if two squares form a valid diagonal
     *
     * @param from Starting square
     * @param to Destination square
     * @return true If squares form a proper diagonal (equal rank and file differences)
     */
    bool is_valid_diag(const Square& from, const Square& to) const;

    /**
     * @brief Checks if the path between two squares on the same rank is clear
     *
     * @param from Starting square
     * @param to Destination square
     * @return true If path is clear and squares are on same rank
     */
    bool is_clear_rank(const Square& from, const Square& to) const;

    /**
     * @brief Checks if the path between two squares on the same file is clear
     *
     * @param from Starting square
     * @param to Destination square
     * @return true If path is clear and squares are on same file
     */
    bool is_clear_file(const Square& from, const Square& to) const;

    /**
     * @brief Checks if the diagonal path between two squares is clear
     *
     * @param from Starting square
     * @param to Destination square
     * @return true If path is clear and squares form a valid diagonal
     */
    bool is_clear_diag(const Square& from, const Square& to) const;

    /**
     * @brief Destroys the board and deallocates all squares
     *
     * Properly cleans up all dynamically allocated Square objects
     */
    ~Board();

  private:
    Square* _squares[SIZE][SIZE] = {};
};

/**
 * @brief Output operator for the board
 *
 * @param os Output stream
 * @param board Board to output
 * @return Reference to the output stream
 */
std::ostream& operator<<(std::ostream& os, const Board& board);

#endif // BOARD_H_
