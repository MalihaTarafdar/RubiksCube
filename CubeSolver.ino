/* RUBIKS CUBE SOLVER
 *  
 * KEY
 * 1      white
 * 2      yellow
 * 3      blue
 * 4      green
 * 5      red
 * 6      orange
 */
int motorFront = 3;
int motorUp = 5;
int motorDown = 6;
int motorBack = 9;
int motorLeft = 10;
int motorRight = 11;
int buttonPin1 = 2;
int buttonPin2 = 4;
String solution = "";
int cube[9][12] = {{2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
               {2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
               {2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
               {4, 4, 4, 6, 6, 6, 3, 3, 3, 5, 5, 5},
               {4, 4, 4, 6, 6, 6, 3, 3, 3, 5, 5, 5},
               {4, 4, 4, 6, 6, 6, 3, 3, 3, 5, 5, 5},
               {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
               {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
               {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
void setup() {
  pinMode(buttonPin1, INPUT);
  pinMode(buttonPin2, INPUT);
  pinMode(motorFront, OUTPUT);
  pinMode(motorUp, OUTPUT);
  pinMode(motorDown, OUTPUT);
  pinMode(motorBack, OUTPUT);
  pinMode(motorLeft, OUTPUT);
  pinMode(motorRight, OUTPUT);
  Serial.begin(9600);
}
void scramble() {
  String scramble = "";
  for (int i = 0; i < 15; i++) {
      int rando = (rand() % 12) + 1;
      switch (rando) {
        case 1: down(true);
                scramble += "D ";
        break;
        case 2: downPrime(true);
                scramble += "D' ";
        break;
        case 3: up(true);
                scramble += "U ";
        break;
        case 4: upPrime(true);
                scramble += "U' ";
        break;
        case 5: back(true);
                scramble += "B ";
        break;
        case 6: backPrime(true);
                scramble += "B' ";
        break;
        case 7: front(true);
                scramble += "F ";
        break;
        case 8: frontPrime(true);
                scramble += "F' ";
        break;
        case 9: left(true);
                scramble += "L ";
        break;
        case 10: leftPrime(true);
                scramble += "L' ";
        break;
        case 11: right(true);
                scramble += "R ";
        break;
        case 12: rightPrime(true);
                scramble += "R' ";
        break;
      }
    }
}
void opcontrol() {
  /*while (true) {
    String turn;
    switch (turn) {
        case "r": right(false);
        break;
        case "r'": rightPrime(false);
        break;
        case "l": left(false);
        break;
        case "l'": leftPrime(false);
        break;
        case "u": up(false);
        break;
        case "u'": upPrime(false);
        break;
        case "d": down(false);
        break;
        case "d'": downPrime(false);
        break;
        case "f": front(false);
        break;
        case "f'": frontPrime(false);
        break;
        case "b": back(false);
        break;
        case "b'": backPrime(false);
        break;
        case "q": goto afterLoop;
      }
  }
  afterLoop:*/
}
void cross() {
  boolean done = false;
    while (!done) {
      int* index = searchFace(1, 0, 0, true);
      if (index[0] != -1) { //search top face
        if (cube[2][1] == 1) {
          if (cube[3][1] == 3) {
            up(false);
            up(false);
          } else if (cube[3][1] == 5) {
            up(false);
          } else if (cube[3][1] == 6) {
            upPrime(false);
          }
        } else if (cube[0][1] == 1) {
          if (cube[3][7] == 4) {
            up(false);
            up(false);
          } else if (cube[3][7] == 6) {
            up(false);
          } else if (cube[3][7] == 5) {
            upPrime(false);
          }
        } else if (cube[1][0] == 1) {
          if (cube[3][10] == 6) {
            up(false);
            up(false);
          } else if (cube[3][10] == 3) {
            up(false);
          } else if (cube[3][10] == 4) {
            upPrime(false);
          }
        } else if (cube[1][2] == 1) {
          if (cube[3][4] == 5) {
            up(false);
            up(false);
          } else if (cube[3][4] == 4) {
            up(false);
          } else if (cube[3][4] == 3) {
            upPrime(false);
          }
        }

        if (cube[2][1] == 1 && cube[3][1] == 4) {
          front(false);
          front(false);
        } else if (cube[0][1] == 1 && cube[3][7] == 3) {
          back(false);
          back(false);
        } else if (cube[1][0] == 1 && cube[3][10] == 5) {
          left(false);
          left(false);
        } else if (cube[1][2] == 1 && cube[3][4] == 6) {
          right(false);
          right(false);
        }
      } else {
        index = searchLayer(1, 3, true);
        if (index[0] != -1) { //search top layer
          if (index[1] == 1) {
            while (cube[2][1] != 5 && cube[3][1] != 1) {
              up(false);
            }
            frontPrime(false);
            left(false);
            front(false);
          } else if (index[1] == 7) {
            while (cube[0][1] != 6 && cube[3][7] != 1) {
              up(false);
            }
            backPrime(false);
            right(false);
            back(false);
          } else if (index[1] == 10) {
            while (cube[1][0] != 3 && cube[3][10] != 1) {
              up(false);
            }
            leftPrime(false);
            back(false);
            left(false);
          } else if (index[1] == 4) {
            while (cube[1][2] != 4 && cube[3][4] != 1) {
              up(false);
            }
            rightPrime(false);
            front(false);
            right(false);
          }
        } else {
          index = searchLayer(1, 5, true);
          if (index[0] != -1) { //search bottom layer
            if (index[1] == 1) {
              front(false);
              front(false);
            } else if (index[1] == 4) {
              right(false);
              right(false);
            } else if (index[1] == 7) {
              back(false);
              back(false);
            } else if (index[1] == 10) {
              left(false);
              left(false);
            }
          } else { //take out if in middle layer
            if (cube[4][2] == 1 || cube[4][3] == 1) {
              right(false);
              up(false);
              rightPrime(false);
            } else if (cube[4][5] == 1 || cube[4][6] == 1) {
              rightPrime(false);
              upPrime(false);
              right(false);
            } else if (cube[4][8] == 1 || cube[4][9] == 1) {
              left(false);
              up(false);
              leftPrime(false);
            } else if (cube[4][0] == 1 || cube[4][11] == 1) {
              leftPrime(false);
              upPrime(false);
              left(false);
            } else { //take out if flipped
              if (cube[6][1] == 1 && cube[7][0] == cube[6][1] && cube[7][2] == cube[6][1] && cube[8][1] == cube[6][1]) {
                if (cube[5][1] != 4) {
                  front(false);
                  front(false);
                }
                if (cube[5][4] != 6) {
                  right(false);
                  right(false);
                }
                if (cube[5][7] != 3) {
                  back(false);
                  back(false);
                }
                if (cube[5][10] != 5) {
                  left(false);
                  left(false);
                }
              }
            }
          }
        }
      }
      if (cube[6][1] == 1 && cube[7][0] == 1 && cube[7][2] == 1 && cube[8][1] == 1) {
        if (cube[5][1] == 4 && cube[5][4] == 6 && cube[5][7] == 3 && cube[5][10] == 5) {
          done = true;
        } else {
          done = false;
        }
      }
    }
}
void corners() {
  boolean done = false;
    while (!done) {
      int* index = searchLayer(1, 3, false);
      if (index[0] != -1) { //search top layer
        if (cube[3][2] == 1 && cube[3][3] == 6) {
          up(false);
          right(false);
          upPrime(false);
          rightPrime(false);
        }
        if (cube[3][3] == 1 && cube[3][2] == 4) {
          right(false);
          up(false);
          rightPrime(false);
        }
        if (cube[3][5] == 1 && cube[3][6] == 3) {
          rightPrime(false);
          upPrime(false);
          right(false);
        }
        if (cube[3][6] == 1 && cube[3][5] == 6) {
          upPrime(false);
          rightPrime(false);
          up(false);
          right(false);
        }
        if (cube[3][8] == 1 && cube[3][9] == 5) {
          up(false);
          left(false);
          upPrime(false);
          leftPrime(false);
        }
        if (cube[3][9] == 1 && cube[3][8] == 3) {
          left(false);
          up(false);
          leftPrime(false);
        }
        if (cube[3][11] == 1 && cube[3][0] == 4) {
          leftPrime(false);
          upPrime(false);
          left(false);
        }
        if (cube[3][0] == 1 && cube[3][11] == 5) {
          upPrime(false);
          leftPrime(false);
          up(false);
          left(false);
        }
        up(false);
      } else {
        index = searchFace(1, 0, 0, false);
        if (index[0] != -1) { //search top face
          if (cube[0][0] == 1 && cube[8][0] != 1) {
            left(false);
            upPrime(false);
            leftPrime(false);
          } else if (cube[0][2] == 1 && cube[8][2] != 1) {
            rightPrime(false);
            up(false);
            right(false);
          } else if (cube[2][0] == 1 && cube[6][0] != 1) {
            leftPrime(false);
            up(false);
            left(false);
          } else if (cube[2][2] == 1 && cube[6][2] != 1) {
            right(false);
            upPrime(false);
            rightPrime(false);
          }
          up(false);
        } else {
          index = searchLayer(1, 5, false);
          if (index[0] != -1) { //search bottom layer
            if (cube[5][0] == 1 || cube[5][11] == 1) {
              leftPrime(false);
              upPrime(false);
              left(false);
            }
            if (cube[5][2] == 1 || cube[5][3] == 1) {
              right(false);
              up(false);
              rightPrime(false);
            }
            if (cube[5][5] == 1 || cube[5][6] == 1) {
              rightPrime(false);
              upPrime(false);
              right(false);
            }
            if (cube[5][8] == 1 || cube[5][9] == 1) {
              left(false);
              up(false);
              leftPrime(false);
            }
          } else { //fix bottom face
            if (cube[6][0] == 1 && cube[6][2] == 1 && cube[8][0] == 1 && cube[8][2] == 1) {
              if (cube[5][0] != 4) {
                leftPrime(false);
                upPrime(false);
                left(false);
              }
              if (cube[5][2] != 4) {
                right(false);
                up(false);
                rightPrime(false);
              }
              if (cube[5][6] != 3) {
                rightPrime(false);
                upPrime(false);
                right(false);
              }
              if (cube[5][8] != 3) {
                left(false);
                up(false);
                leftPrime(false);
              }
            }
          }
        }
      }
      if (cube[6][0] == 1 && cube[6][2] == 1 && cube[8][0] == 1 && cube[8][2] == 1) {
        if (cube[5][0] == 4 && cube[5][2] == 4 &&
            cube[5][3] == 6 && cube[5][5] == 6 &&
            cube[5][6] == 3 && cube[5][8] == 3 &&
            cube[5][9] == 5 && cube[5][11] == 5) {
              done = true;
            }
      } else {
        done = false;
      }
    }
}
void middleLayer() {
  boolean done = false;
    while (!done) {
      boolean done2 = false;
      while (!done2) { //search top layer
        if (cube[3][7] == 3 && cube[0][1] == 6) {
          upPrime(false);
          rightPrime(false);
          up(false);
          right(false);
          up(false);
          back(false);
          upPrime(false);
          backPrime(false);
        } else if (cube[3][7] == 3 && cube[0][1] == 5) {
          up(false);
          left(false);
          upPrime(false);
          leftPrime(false);
          upPrime(false);
          backPrime(false);
          up(false);
          back(false);
        } else if (cube[3][10] == 5 && cube[1][0] == 4) {
          up(false);
          front(false);
          upPrime(false);
          frontPrime(false);
          upPrime(false);
          leftPrime(false);
          up(false);
          left(false);
        } else if (cube[3][10] == 5 && cube[1][0] == 3) {
          upPrime(false);
          backPrime(false);
          up(false);
          back(false);
          up(false);
          left(false);
          upPrime(false);
          leftPrime(false);
        } else if (cube[3][4] == 6 && cube[1][2] == 3) {
          up(false);
          back(false);
          upPrime(false);
          backPrime(false);
          upPrime(false);
          rightPrime(false);
          up(false);
          right(false);
        } else if (cube[3][4] == 6 && cube[1][2] == 4) {
          upPrime(false);
          frontPrime(false);
          up(false);
          front(false);
          up(false);
          right(false);
          upPrime(false);
          rightPrime(false);
        } else if (cube[3][1] == 4 && cube[2][1] == 6) {
          up(false);
          right(false);
          upPrime(false);
          rightPrime(false);
          upPrime(false);
          frontPrime(false);
          up(false);
          front(false);
        } else if (cube[3][1] == 4 && cube[2][1] == 5) {
          upPrime(false);
          leftPrime(false);
          up(false);
          left(false);
          up(false);
          front(false);
          upPrime(false);
          frontPrime(false);
        }
        for (int i = 3; i < 7; i++) {
          for (int j = 3; j < 7; j++) {
            if (cube[3][1] == i && cube[2][1] == i) {
              continue;
            }
            if (cube[3][1] == i && cube[2][1] == j) {
              done2 = false;
              goto innerLoop;
            } else {
              done2 = true;
            }

            if (cube[3][4] == i && cube[1][2] == i) {
              continue;
            }
            if (cube[3][4] == i && cube[1][2] == j) {
              done2 = false;
              goto innerLoop;
            } else {
              done2 = true;
            }

            if (cube[3][7] == i && cube[0][1] == i) {
              continue;
            }
            if (cube[3][7] == i && cube[0][1] == j) {
              done2 = false;
              goto innerLoop;
            } else {
              done2 = true;
            }

            if (cube[3][10] == i && cube[1][0] == i) {
              continue;
            }
            if (cube[3][10] == i && cube[1][0] == j) {
              done2 = false;
              goto innerLoop;
            } else {
              done2 = true;
            }
          }
        }
        innerLoop:
        up(false);
      } //take out if wrong
      if (cube[4][0] != 4 || cube[4][11] != 5) {
        leftPrime(false);
        up(false);
        left(false);
        up(false);
        front(false);
        upPrime(false);
        frontPrime(false);
      } else if (cube[4][2] != 4 || cube[4][3] != 6) {
        right(false);
        upPrime(false);
        rightPrime(false);
        upPrime(false);
        frontPrime(false);
        up(false);
        front(false);
      } else if (cube[4][6] != 3 || cube[4][5] != 6) {
        rightPrime(false);
        up(false);
        right(false);
        up(false);
        back(false);
        upPrime(false);
        backPrime(false);
      } else if (cube[4][8] != 3 || cube[4][9] != 5) {
        left(false);
        upPrime(false);
        leftPrime(false);
        upPrime(false);
        backPrime(false);
        up(false);
        back(false);
      }
      if (cube[4][2] == 4 && cube[4][3] == 6 &&
          cube[4][5] == 6 && cube[4][6] == 3 &&
          cube[4][8] == 3 && cube[4][9] == 5 &&
          cube[4][11] == 5 && cube[4][0] == 4) {
        done = true;
      } else {
        done = false;
      }
    }
}
void oll() {
  boolean done = false;
    while (!done) {
      int* index = searchFace(2, 0, 0, true);
      if (cube[0][1] != 2 || cube[1][0] != 2 || cube[1][2] != 2 || cube[2][1] != 2) {
        if (index[0] == 0 && cube[2][1] == 2) { //vertical line
          up(false);
          front(false);
          right(false);
          up(false);
          rightPrime(false);
          upPrime(false);
          frontPrime(false);
        } else if (cube[1][0] == 2 && cube[1][2] == 2 && cube[2][1] != 2) { //horizontal line
          front(false);
          right(false);
          up(false);
          rightPrime(false);
          upPrime(false);
          frontPrime(false);
        } else if (cube[0][1] == 2 && cube[1][2] == 2 && cube[1][0] != 2) {
          upPrime(false);
        } else if (cube[1][0] == 2 && cube[2][1] == 2 && cube[1][2] != 2) {
          up(false);
        } else if (cube[2][1] == 2 && cube[1][2] == 2 && cube[0][1] != 2) {
          up(false);
          up(false);
        } else { //dot case and half cross case
        front(false);
        up(false);
        right(false);
        upPrime(false);
        rightPrime(false);
        frontPrime(false);
      }
    } else { //cross cases
      index = searchLayer(2, 3, false);
      int* index2 = searchFace(2, 0, 0, false);
      if ((cube[0][0] == 2 && cube[0][2] != 2 && cube[2][0] != 2 && cube[2][2] != 2) ||
          (cube[0][2] == 2 && cube[0][0] != 2 && cube[2][0] != 2 && cube[2][2] != 2) ||
          (cube[2][0] == 2 && cube[0][2] != 2 && cube[0][0] != 2 && cube[2][2] != 2) ||
          (cube[2][2] == 2 && cube[0][2] != 2 && cube[2][0] != 2 && cube[0][0] != 2)) {
        if (cube[0][0] == 2) {
          upPrime(false);
        } else if (cube[0][2] == 2) {
          up(false);
          up(false);
        } else if (cube[2][2] == 2) {
          up(false);
        }
      } else {
        if (cube[0][0] != 2 && cube[0][2] != 2 && cube[2][0] != 2 && cube[2][2] != 2) {
          while (cube[3][11] != 2) {
            up(false);
          }
        } else {
          while (cube[3][0] != 2) {
            up(false);
          }
        }
      }
      right(false);
      up(false);
      rightPrime(false);
      up(false);
      right(false);
      up(false);
      up(false);
      rightPrime(false);
    }
    int count = 0;
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          if (cube[i][j] == 2) {
            count++;
          }
        }
      }
      if (count >= 8) {
        done = true;
      }
    }
}
void pll() {
  boolean done = false;
    while (!done) {
      if (cube[3][0] != cube[3][2] || cube[3][3] != cube[3][5] || cube[3][6] != cube[3][8]) { //if not all headlights
        if (cube[3][0] == cube[3][2] ||
            cube[3][3] == cube[3][5] ||
            cube[3][6] == cube[3][8] ||
            cube[3][9] == cube[3][11]) { //if 1 headlight
          while (cube[3][6] != cube[3][8]) { //move to back
            up(false);
          }
          rightPrime(false);
          front(false);
          rightPrime(false);
          back(false);
          back(false);
          right(false);
          frontPrime(false);
          rightPrime(false);
          back(false);
          back(false);
          right(false);
          right(false);
          upPrime(false);
        } else { //if no headlights
          rightPrime(false);
          front(false);
          rightPrime(false);
          back(false);
          back(false);
          right(false);
          frontPrime(false);
          rightPrime(false);
          back(false);
          back(false);
          right(false);
          right(false);
          upPrime(false);
        }
      } else { //if all headlights
        if (cube[3][0] == cube[3][1] ||
            cube[3][3] == cube[3][4] ||
            cube[3][6] == cube[3][7] ||
            cube[3][9] == cube[3][10]) { //if bar
          while (cube[3][6] != cube[3][7]) { //move bar to back
            up(false);
          }
        }
        right(false);
        upPrime(false);
        right(false);
        up(false);
        right(false);
        up(false);
        right(false);
        upPrime(false);
        rightPrime(false);
        upPrime(false);
        right(false);
        right(false);
      }
      for (int i = 0; i < 12; i += 3) {
        if (cube[3][i] == cube[3][i + 1] && cube[3][i + 1] == cube[3][i + 2]) {
          done = true;
        } else {
          done = false;
          break;
        }
      }
    }
    if (cube[3][0] == 3) {
      up(false);
      up(false);
    } else if (cube[3][0] == 5) {
      up(false);
    } else if (cube[3][0] == 6) {
      upPrime(false);
    }
}
int *searchFace(int color, int row, int col, boolean edge) {
  static int index[2];
  for (int i = row; i < row + 3; i++) {
      for (int j = col; j < col + 3; j++) {
        if (cube[i][j] == color) {
          if (i == row + 1 && j == col + 1) {
            continue;
          }
          if (edge &&
          (((i == row + 1) && (j == col)) ||
          ((i == row) && (j == col + 1)) ||
          ((i == row + 1) && (j == col + 2)) ||
          ((i == row + 2) && (j == col + 1)))) {
            index[0] = i;
            index[1] = j;
            return index;
          }
          if (!edge &&
          (((i == row) && (j == col)) ||
          ((i == row) && (j == col + 2)) ||
          ((i == row + 2) && (j == col + 2)) ||
          ((i == row + 2) && (j == col)))) {
            index[0] = i;
            index[1] = j;
            return index;
          }
        } else {
          index[0] = -1;
          index[1] = -1;
        }
      }
    }
    return index;
}
int *searchLayer(int color, int row, boolean edge) {
  static int index[2];
    for (int i = 0; i < 12; i++) {
      if (cube[row][i] == color) {
        if (row == 4 && (i == 1 || i == 4 || i == 7 || i == 10)) {
          continue;
        }
        if (edge && (row == 3 || row == 5) && i % 3 == 1) {
          index[0] = row;
          index[1] = i;
          return index;
        } else if (edge && (row == 4) && (i % 3 == 0 || i % 3 == 2)) {
          index[0] = row;
          index[1] = i;
          return index;
        }

        if (!edge && (row == 3 || row == 5) && (i % 3 == 0 || i % 3 == 2)) {
          index[0] = row;
          index[1] = i;
          return index;
        }
      } else {
        index[0] = -1;
        index[1] = -1;
      }
    }
    return index;
}
void reset() {
  resetFace(0, 0, 2);
    resetFace(3, 0, 4);
    resetFace(6, 0, 1);
    resetFace(3, 3, 6);
    resetFace(3, 6, 3);
    resetFace(3, 9, 5);
    solution = "";
}
void resetFace(int row, int col, int color) {
    for (int i = row; i < row + 3; i++) {
      for (int j = col; j < col + 3; j++) {
        cube[i][j] = color;
      }
    }
  }
void turn(int motorPin, int speed1) {
  analogWrite(motorPin, speed1);
  delay(250);
  analogWrite(motorPin, 0);
}
void right(boolean scramble) {
  swapCol(3, 2, 6, 2);
  swapCol(6, 2, 3, 6);
  swapCol(3, 6, 0, 2);
  moveFaceCW(3, 3);
  swap(3, 6, 5, 6);
  swap(6, 2, 8, 2);
  if (!scramble)
    solution += "R ";
}
void rightPrime(boolean scramble) {
  swapCol(3, 2, 0, 2);
  swapCol(0, 2, 3, 6);
  swapCol(3, 6, 6, 2);
  moveFaceCCW(3, 3);
  swap(0, 2, 2, 2);
  swap(3, 6, 5, 6);
  if (!scramble)
    solution += "R' ";
}
void left(boolean scramble) {
  swapCol(6, 0, 3, 0);
  swapCol(3, 0, 0, 0);
  swapCol(0, 0, 3, 8);
  moveFaceCW(3, 9);
  swap(0, 0, 2, 0);
  swap(3, 8, 5, 8);
  if (!scramble)
    solution += "L ";
}
void leftPrime(boolean scramble) {
  swapCol(0, 0, 3, 0);
  swapCol(3, 0, 3, 8);
  swapCol(3, 0, 6, 0);
  moveFaceCCW(3, 9);
  swap(6, 0, 8, 0);
  swap(3, 8, 5, 8);
  if (!scramble)
    solution += "L' ";
}
void up(boolean scramble) {
  moveRow(3, 3);
  moveFaceCW(0, 0);
  if (!scramble)
    solution += "U ";
}
void upPrime(boolean scramble) {
  moveRow(3, 9);
  moveFaceCCW(0, 0);
  if (!scramble)
    solution += "U' ";
}
void down(boolean scramble) {
  moveRow(5, 9);
  moveFaceCW(6, 0);
  if (!scramble)
    solution += "D ";
}
void downPrime(boolean scramble) {
  moveRow(5, 3);
  moveFaceCCW(6, 0);
  if (!scramble)
    solution += "D' ";
}
void front(boolean scramble) {
  swapRowWithCol(2, 0, 3, 11);
  swapRowWithCol(6, 0, 3, 11);
  swapRowWithCol(6, 0, 3, 3);
  moveFaceCW(3, 0);
  swap(2, 0, 2, 2);
  swap(6, 0, 6, 2);
  if (!scramble)
    solution += "F ";
}
void frontPrime(boolean scramble) {
  swapRowWithCol(2, 0, 3, 3);
  swapRowWithCol(6, 0, 3, 3);
  swapRowWithCol(6, 0, 3, 11);
  moveFaceCCW(3, 0);
  swap(3, 3, 5, 3);
  swap(3, 11, 5, 11);
  if (!scramble)
    solution += "F' ";
}
void back(boolean scramble) {
  swapRowWithCol(0, 0, 3, 5);
  swapRowWithCol(8, 0, 3, 5);
  swapRowWithCol(8, 0, 3, 9);
  moveFaceCW(3, 6);
  swap(3, 5, 5, 5);
  swap(3, 9, 5, 9);
  if (!scramble)
    solution += "B ";
}
void backPrime(boolean scramble) {
  swapRowWithCol(0, 0, 3, 9);
  swapRowWithCol(8, 0, 3, 9);
  swapRowWithCol(8, 0, 3, 5);
  moveFaceCCW(3, 6);
  swap(0, 0, 0, 2);
  swap(8, 0, 8, 2);
  if (!scramble)
    solution += "B' ";
}
void moveRow(int row, int direction1) {
  for (int i = 0; i < direction1; i++) {
      int j;
      int temp = cube[row][0];
      for (j = 0; j < 11; j++) {
        cube[row][j] = cube[row][j + 1];
      }
      cube[row][j] = temp;
    }
}
void swap(int row1, int col1, int row2, int col2) {
  int temp = cube[row1][col1];
    cube[row1][col1] = cube[row2][col2];
    cube[row2][col2] = temp;
}
void swapCol(int row1, int col1, int row2, int col2) {
  for (int i = row1; i < row1 + 3; i++) {
      int temp = cube[i][col1];
      cube[i][col1] = cube[row2][col2];
      cube[row2][col2] = temp;
      row2++;
    }
}
void swapRowWithCol(int row1, int col1, int row2, int col2) {
  for (int i = col1; i < col1 + 3; i++) {
      int temp = cube[row1][i];
      cube[row1][i] = cube[row2][col2];
      cube[row2][col2] = temp;
      row2++;
    }
}
void moveFaceCW(int row, int col) {
  int temp = cube[row][col];
    cube[row][col] = cube[row + 2][col];
    cube[row + 2][col] = cube[row + 2][col + 2];
    cube[row + 2][col + 2] = cube[row][col + 2];
    cube[row][col + 2] = temp;

    temp = cube[row][col + 1];
    cube[row][col + 1] = cube[row + 1][col];
    cube[row + 1][col] = cube[row + 2][col + 1];
    cube[row + 2][col + 1] = cube[row + 1][col + 2];
    cube[row + 1][col + 2] = temp;
}
void moveFaceCCW(int row, int col) {
  int temp = cube[row][col];
    cube[row][col] = cube[row][col + 2];
    cube[row][col + 2] = cube[row + 2][col + 2];
    cube[row + 2][col + 2] = cube[row + 2][col];
    cube[row + 2][col] = temp;

    temp = cube[row][col + 1];
    cube[row][col + 1] = cube[row + 1][col + 2];
    cube[row + 1][col + 2] = cube[row + 2][col + 1];
    cube[row + 2][col + 1] = cube[row + 1][col];
    cube[row + 1][col] = temp;
}
void loop() {
  if (digitalRead(buttonPin1) == HIGH) {
    turn(motorRight, 150);
  } else if (digitalRead(buttonPin2 == HIGH)) {
    //opcontrol();
  }
}

