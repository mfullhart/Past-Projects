#ifndef PIECE_H_
#define PIECE_H_

#include <iostream>

class Square;

typedef unsigned int piece_value_t;

/**
 * @class Piece
 * @brief Abstract base class for chess pieces
 *
 * Provides common functionality and interface for all chess pieces.
 */
class Piece {
  public:
    /**
     * @enum Color
     * @brief Represents piece colors
     */
    enum struct Color {
        black, white
    };

    /**
     * @brief Constructs a new piece
     *
     * @param color The piece color (black/white)
     * @param location Initial square location
     */
    Piece(const Piece::Color color, Square& location) : _color(color) {
        this->set_location(&location);
    }

    /**
     * @brief Gets the piece's value
     *
     * @return The piece's numerical value
     */
    virtual piece_value_t value() const = 0;

    /**
     * @brief Gets the piece's color
     *
     * @return The piece color
     */
    Piece::Color color() const;

    /**
     * @brief Gets the piece's current location
     *
     * @return Pointer to current square (nullptr if not on board)
     */
    Square* location() const;

    /**
     * @brief Sets the piece's location
     *
     * @param location New square location
     */
    void set_location(Square* location);

    /**
     * @brief Checks if piece is on a square
     *
     * @return True if on board, false otherwise
     */
    bool is_on_square() const;

    /**
     * @brief Checks if piece can move to a square
     *
     * @param location Destination square
     * @return True if move is valid, false otherwise
     */
    virtual bool can_move_to(const Square& location) const = 0;

    /**
     * @brief Moves piece to a new square
     *
     * @param location Destination square
     * @return True if move succeeded, false otherwise
     */
    virtual bool move_to(Square& location);

    /**
     * @brief Captures the piece (removes from board)
     */
    void capture();

    /**
     * @brief Gets the piece's string representation
     *
     * @return String representation (single character)
     */
    virtual std::string str() const = 0;

    // Virtual destructor
    virtual ~Piece() = default;

  private:
    const Piece::Color _color;
    Square* _location = nullptr;
};

/**
 * @brief Output operator for pieces
 *
 * @param os Output stream
 * @param piece Piece to output
 * @return Reference to the output stream
 */
std::ostream& operator<<(std::ostream& os, const Piece& piece);


#endif // PIECE_H_
