#ifndef PLAYER_H_
#define PLAYER_H_

#include <vector>
#include <string>
#include "Piece.h"
#include "Board.h"
#include "King.h"

class Player {
public:
    /**
     * @brief Constructs a new Player with the given color and board reference
     * @param color The color of the player's pieces (white or black)
     * @param board Reference to the game board
     */
    Player(Piece::Color color, const Board& board);

    /**
     * @brief Gets the player's color
     * @return Piece::Color representing the player's color
     */
    Piece::Color color() const;

    /**
     * @brief Attempts to make a move from one square to another
     * @param from The starting position in algebraic notation (e.g., "e2")
     * @param to The destination position in algebraic notation (e.g., "e4")
     * @return true if the move was valid and executed, false otherwise
     */
    bool make_move(const std::string& from, const std::string& to);

    /**
     * @brief Calculates the total value of the player's remaining pieces
     * @return The sum of values of all active pieces
     */
    piece_value_t piece_value() const;

    /**
     * @brief Destructor - cleans up all pieces owned by the player
     */
    ~Player();

    /**
     * @brief Checks if the player is in check
     * @param board Reference to the game board
     * @return True if king is under attack, false otherwise
     */
    bool is_in_check(const Board& board) const;

    /**
     * @brief Checks if the player is in checkmate
     * @param board Reference to the game board
     * @return True if no valid moves exist that get out of check
     */
    bool is_in_checkmate(const Board& board) const;

    /**
     * @brief Gets all possible moves for all pieces
     * @param board Reference to the game board
     * @return Vector of valid move strings (e.g., {"e2e4", "g1f3"})
     */
    std::vector<std::string> get_all_valid_moves(const Board& board) const;

    /**
     * @brief Gets the player's king
     * @return Pointer to the king piece
     */
    King* get_king() const { return _king; }

private:
    const Piece::Color _color;        // The color of the player's pieces
    const Board& _board;              // Reference to the game board
    std::vector<Piece*> _pieces;      // Collection of all pieces owned by the player
    King* _king;                      // Pointer to the player's king (special handling)

    /**
     * @brief Checks if a move would leave king in check
     * @param from Starting square
     * @param to Destination square
     * @param board Reference to the game board
     * @return True if move is safe, false if leaves king in check
     */
    bool is_move_safe(const Square& from, const Square& to, Board& board) const;
};

#endif // PLAYER_H_
