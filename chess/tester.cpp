#include "Board.h"
#include "Board.cpp"
#include "King.h"
#include "King.cpp"
#include "Queen.h"
#include "Queen.cpp"
#include "Rook.h"
#include "Rook.cpp"
#include "Bishop.h"
#include "Bishop.cpp"
#include "Knight.h"
#include "Knight.cpp"
#include "Pawn.h"
#include "Pawn.cpp"
#include "Square.h"
#include "Square.cpp"
#include "Piece.h"
#include "Piece.cpp"
#include <iostream>
#include <string>
#include <sstream>
#include <limits>

void Chess::runGame() {
    Board board;
    Player white(Piece::Color::white, board);
    Player black(Piece::Color::black, board);

    int turnCount = 0;
    bool whiteTurn = true;
    bool gameOver = false;

    while (turnCount < 20 && !gameOver) {
        // Print the current board state with turn information
        std::cout << "\nTurn " << (turnCount + 1) << " - ";
        std::cout << (whiteTurn ? "White" : "Black") << " to move\n";
        std::cout << board << std::endl;

        Player& currentPlayer = whiteTurn ? white : black;
        std::string move;
        bool validMove = false;

        while (!validMove && !gameOver) {
            std::cout << "Enter move (e.g., 'a2 a4') or 'quit' to end: ";
            std::getline(std::cin, move);

            // Check for quit command
            if (move == "quit" || move == "exit") {
                gameOver = true;
                break;
            }

            std::istringstream iss(move);
            std::string from, to;
            if (iss >> from >> to) {
                try {
                    validMove = currentPlayer.make_move(from, to);
                    if (!validMove) {
                        std::cout << "Invalid move. Try again.\n";
                    }
                } catch (const std::exception& e) {
                    std::cout << "Error: " << e.what() << ". Try again.\n";
                    // Clear any error state from cin
                    std::cin.clear();
                    std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
                }
            } else {
                std::cout << "Invalid input format. Please enter two positions separated by space.\n";
            }
        }

        if (!gameOver) {
            whiteTurn = !whiteTurn;
            turnCount++;
        }
    }

    // Final game state
    if (turnCount >= 20) {
        std::cout << "\nGame ended after 20 turns.\n";
    } else {
        std::cout << "\nGame ended by player.\n";
    }

    std::cout << "Final board state:\n";
    std::cout << board << std::endl;
    std::cout << "White pieces value: " << white.piece_value() << std::endl;
    std::cout << "Black pieces value: " << black.piece_value() << std::endl;

    // Determine the result
    if (white.piece_value() > black.piece_value()) {
        std::cout << "White wins by material advantage!\n";
    } else if (black.piece_value() > white.piece_value()) {
        std::cout << "Black wins by material advantage!\n";
    } else {
        std::cout << "The game is a draw!\n";
    }
}

int main() {
    try {
        Chess::runGame();
    } catch (const std::exception& e) {
        std::cerr << "Fatal error: " << e.what() << std::endl;
        return 1;
    }
    return 0;
}