#ifndef CHESS_H_
#define CHESS_H_

#include "Board.h"
#include "Player.h"

/**
 * @class Chess
 *
 * @brief Main controller class for the chess game
 *
 * This class manages the game cycle
 */
class Chess {
	public:
		/**
	     * @brief Starts and runs the chess game
	     *
	     * Handles the complete game loop
	     */
		static void runGame();
};

#endif // CHESS_H_
