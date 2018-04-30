import java.util.*;

public class CubeSimulation {

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_WHITE = "\u001B[37m";

  public static int motorFront = 3;
  public static int motorUp = 5;
  public static int motorDown = 6;
  public static int motorBack = 9;
  public static int motorLeft = 10;
  public static int motorRight = 11;
  /*
   * KEY
   * 1      white
   * 2      yellow
   * 3      blue
   * 4      green
   * 5      red
   * 6      purple(orange)
   */
  public static int[][] cube = {{2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                 {2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                 {2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                 {4, 4, 4, 6, 6, 6, 3, 3, 3, 5, 5, 5},
                                 {4, 4, 4, 6, 6, 6, 3, 3, 3, 5, 5, 5},
                                 {4, 4, 4, 6, 6, 6, 3, 3, 3, 5, 5, 5},
                                 {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                 {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                 {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
  public static String solution = ""; //TODO
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int choice = 3;
    while (choice != 0) {
      reset();
      System.out.println("\nPress 1 for auto-solve. Press 2 for operator control. Press 0 to quit.");
      choice = input.nextInt();
      if (choice == 1) {
        printCube(cube);
        scramble();
        cross();
        printCube(cube);
        System.out.println("Cross complete!\n");
        corners();
        printCube(cube);
        System.out.println("Corners complete!\n");
        f2l();
        printCube(cube);
        System.out.println("F2L complete!\n");
        oll();
        printCube(cube);
        System.out.println("OLL complete!\n");
        pll();
        printCube(cube);
        System.out.println("PLL complete!\n");
        System.out.println(solution + "\n");
      } else if (choice == 2) {
        scramble();
        opcontrol();
      }
    }
  }
  public static void opcontrol() {
    Scanner input = new Scanner(System.in);
    loop:
    while (true) {
      System.out.print("Enter a turn (or q to quit): ");
      String turn = input.nextLine();
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
        case "q": break loop;
      }
      printCube(cube);
    }
  }
  public static void scramble() {
    String scramble = "";
    for (int i = 0; i < 15; i++) {
      int rand = (int)(Math.random() * 12) + 1;
      switch (rand) {
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
    printCube(cube);
    System.out.println(scramble + "\n");
  }
  public static void cross() {
    boolean done = false;
    while (!done) {
      int[] index = searchFace(1, 0, 0, true);
      //white green pair (2, 1), (3, 1)
      //white blue pair (0, 1), (3, 7)
      //white red pair (1, 0), (3, 10)
      //white orange pair (1, 2), (3, 4)
      if (index[0] != -1) {
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
        //System.out.println("Searching top face");
        //printCube(cube);
      } else {
        index = searchLayer(1, 3, true);
        if (index[0] != -1) {
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
          //System.out.println("Searching top layer");
          //printCube(cube);
        } else {
          index = searchLayer(1, 5, true);
          if (index[0] != -1) {
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
            //System.out.println("Searching bottom layer");
            //printCube(cube);
          } else {
            //System.out.println("Searching middle layer");
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
            } else {
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
              //System.out.println("Searching bottom face");
              //printCube(cube);
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
      //System.out.println("Cross loop");
      //printCube(cube);
    }
  }
  public static void corners() {
    boolean done = false;
    while (!done) {
      int[] index = searchLayer(1, 3, false);
      if (index[0] != -1) {
        //search top layer
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
        //System.out.println("Searching top layer");
      } else {
        index = searchFace(1, 0, 0, false);
        if (index[0] != -1) {
          //search top face
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
          up(false); //can make into while (cube[0][0] != 1)
          //System.out.println("Searching top face");
        } else {
          index = searchLayer(1, 5, false);
          if (index[0] != -1) {
            //search bottom layer
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
            //System.out.println("Searching bottom layer");
          } else {
            //fix bottom face
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
      //printCube(cube);
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
      //System.out.println("Corners loop");
    }
  }
  public static void f2l() {
    boolean done = false;
    while (!done) {
      boolean done2 = false;
      while (!done2) {
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
        //System.out.println("Searching top layer");
        outerLoop:
        for (int i = 3; i < 7; i++) {
          for (int j = 3; j < 7; j++) {
            if (cube[3][1] == i && cube[2][1] == i) {
              continue;
            }
            if (cube[3][1] == i && cube[2][1] == j) {
              done2 = false;
              break outerLoop;
            } else {
              done2 = true;
            }

            if (cube[3][4] == i && cube[1][2] == i) {
              continue;
            }
            if (cube[3][4] == i && cube[1][2] == j) {
              done2 = false;
              break outerLoop;
            } else {
              done2 = true;
            }

            if (cube[3][7] == i && cube[0][1] == i) {
              continue;
            }
            if (cube[3][7] == i && cube[0][1] == j) {
              done2 = false;
              break outerLoop;
            } else {
              done2 = true;
            }

            if (cube[3][10] == i && cube[1][0] == i) {
              continue;
            }
            if (cube[3][10] == i && cube[1][0] == j) {
              done2 = false;
              break outerLoop;
            } else {
              done2 = true;
            }
          }
        }
        //printCube(cube);
        //System.out.println(done2);
        up(false);
      }
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
      //System.out.println("F2L loop");
      //printCube(cube);
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
  public static void oll() {
    boolean done = false;
    while (!done) {
      int[] index = searchFace(2, 0, 0, true);
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
      //System.out.println("Searching not cross cases");
    } else { //cross case
      index = searchLayer(2, 3, false);
      int[] index2 = searchFace(2, 0, 0, false);
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
      //System.out.println("Searching cross cases");
    }
    //System.out.println("OLL loop");
    //printCube(cube);
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
  public static void pll() {
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
          //System.out.println("1 headlight");
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
          //System.out.println("No headlights");
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
        //System.out.println("All headlights");
      }
      //System.out.println("PLL loop");
      //printCube(cube);
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
  public static int[] searchFace(int color, int row, int col, boolean edge) {
    int[] index = new int[2];
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
  public static int[] searchLayer(int color, int row, boolean edge) {
    int[] index = new int[2];
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
  public static void right(boolean scramble) {
    swapCol(3, 2, 6, 2);
    swapCol(6, 2, 3, 6);
    swapCol(3, 6, 0, 2);
    moveFaceCW(3, 3);
    swap(3, 6, 5, 6);
    swap(6, 2, 8, 2);
    if (!scramble)
      solution += "R ";
  }
  public static void rightPrime(boolean scramble) {
    swapCol(3, 2, 0, 2);
    swapCol(0, 2, 3, 6);
    swapCol(3, 6, 6, 2);
    moveFaceCCW(3, 3);
    swap(0, 2, 2, 2);
    swap(3, 6, 5, 6);
    if (!scramble)
      solution += "R' ";
  }
  public static void left(boolean scramble) {
    swapCol(6, 0, 3, 0);
    swapCol(3, 0, 0, 0);
    swapCol(0, 0, 3, 8);
    moveFaceCW(3, 9);
    swap(0, 0, 2, 0);
    swap(3, 8, 5, 8);
    if (!scramble)
      solution += "L ";
  }
  public static void leftPrime(boolean scramble) {
    swapCol(0, 0, 3, 0);
    swapCol(3, 0, 3, 8);
    swapCol(3, 0, 6, 0);
    moveFaceCCW(3, 9);
    swap(6, 0, 8, 0);
    swap(3, 8, 5, 8);
    if (!scramble)
      solution += "L' ";
  }
  public static void up(boolean scramble) {
    moveRow(3, 3);
    moveFaceCW(0, 0);
    if (!scramble)
      solution += "U ";
  }
  public static void upPrime(boolean scramble) {
    moveRow(3, 9);
    moveFaceCCW(0, 0);
    if (!scramble)
      solution += "U' ";
  }
  public static void down(boolean scramble) {
    moveRow(5, 9);
    moveFaceCW(6, 0);
    if (!scramble)
      solution += "D ";
  }
  public static void downPrime(boolean scramble) {
    moveRow(5, 3);
    moveFaceCCW(6, 0);
    if (!scramble)
      solution += "D' ";
  }
  public static void front(boolean scramble) {
    swapRowWithCol(2, 0, 3, 11);
    swapRowWithCol(6, 0, 3, 11);
    swapRowWithCol(6, 0, 3, 3);
    moveFaceCW(3, 0);
    swap(2, 0, 2, 2);
    swap(6, 0, 6, 2);
    if (!scramble)
      solution += "F ";
  }
  public static void frontPrime(boolean scramble) {
    swapRowWithCol(2, 0, 3, 3);
    swapRowWithCol(6, 0, 3, 3);
    swapRowWithCol(6, 0, 3, 11);
    moveFaceCCW(3, 0);
    swap(3, 3, 5, 3);
    swap(3, 11, 5, 11);
    if (!scramble)
      solution += "F' ";
  }
  public static void back(boolean scramble) {
    swapRowWithCol(0, 0, 3, 5);
    swapRowWithCol(8, 0, 3, 5);
    swapRowWithCol(8, 0, 3, 9);
    moveFaceCW(3, 6);
    swap(3, 5, 5, 5);
    swap(3, 9, 5, 9);
    if (!scramble)
      solution += "B ";
  }
  public static void backPrime(boolean scramble) {
    swapRowWithCol(0, 0, 3, 9);
    swapRowWithCol(8, 0, 3, 9);
    swapRowWithCol(8, 0, 3, 5);
    moveFaceCCW(3, 6);
    swap(0, 0, 0, 2);
    swap(8, 0, 8, 2);
    if (!scramble)
      solution += "B' ";
  }
  public static void move(int motorPin, int speed) {
    //analogWrite(motorPin, 150);
    //delay(250);
    //analogWrite(motorPin, 0);
  }
  public static void moveRow(int row, int direction) {        //row: 3 top layer
    for (int i = 0; i < direction; i++) {                     //     4 middle layer
      int j;                                                  //     5 bottom layer
      int temp = cube[row][0];
      for (j = 0; j < cube[row].length - 1; j++) {           //direction: 3 U
        cube[row][j] = cube[row][j + 1];                    //           6 U2
      }                                                       //           9 U'
      cube[row][j] = temp;
    }
  }
  public static void swapRowWithCol(int row1, int col1, int row2, int col2) {
    for (int i = col1; i < col1 + 3; i++) {
      int temp = cube[row1][i];
      cube[row1][i] = cube[row2][col2];
      cube[row2][col2] = temp;
      row2++;
    }
  }
  public static void swap(int row1, int col1, int row2, int col2) {
    int temp = cube[row1][col1];
    cube[row1][col1] = cube[row2][col2];
    cube[row2][col2] = temp;
  }
  public static void swapCol(int row1, int col1, int row2, int col2) {
    for (int i = row1; i < row1 + 3; i++) {
      int temp = cube[i][col1];
      cube[i][col1] = cube[row2][col2];
      cube[row2][col2] = temp;
      row2++;
    }
  }
  public static void moveFaceCW(int row, int col) {
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
  public static void moveFaceCCW(int row, int col) {
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
  public static void reset() {
    resetFace(0, 0, 2);
    resetFace(3, 0, 4);
    resetFace(6, 0, 1);
    resetFace(3, 3, 6);
    resetFace(3, 6, 3);
    resetFace(3, 9, 5);
    solution = "";
  }
  public static void resetFace(int row, int col, int color) {
    for (int i = row; i < row + 3; i++) {
      for (int j = col; j < col + 3; j++) {
        cube[i][j] = color;
      }
    }
  }
  public static void printCube(int[][] cube) {
    for (int i = 0; i < cube.length; i++) {
      for (int j = 0; j < cube[0].length; j++) {
        switch (cube[i][j]) {
          case 1: System.out.print(ANSI_WHITE + cube[i][j] + ANSI_RESET);
          break;
          case 2: System.out.print(ANSI_YELLOW + cube[i][j] + ANSI_RESET);
          break;
          case 3: System.out.print(ANSI_BLUE + cube[i][j] + ANSI_RESET);
          break;
          case 4: System.out.print(ANSI_GREEN + cube[i][j] + ANSI_RESET);
          break;
          case 5: System.out.print(ANSI_RED + cube[i][j] + ANSI_RESET);
          break;
          case 6: System.out.print(ANSI_PURPLE + cube[i][j] + ANSI_RESET);
          break;
          case 0: System.out.print(" ");
          break;
        }
      }
      System.out.println();
    }
    System.out.println();
  }
}
