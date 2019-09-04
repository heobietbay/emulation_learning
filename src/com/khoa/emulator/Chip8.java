package com.khoa.emulator;

public class Chip8 {
    // MEMORY
    char memory[] = new char[4096];
    short opcode;

    //REGISTERS
    /*
    CHIP-8 has 16 8-bit data registers named V0 to VF.
    The VF register doubles as a flag for some instructions; thus, it should be avoided.
    In an addition operation, VF is the carry flag, while in subtraction, it is the "no borrow" flag.
    In the draw instruction VF is set upon pixel collision.
    */
    char[] registersV = new char[16];

    //The address register, which is named I,
    // is 16 bits wide and is used with several opcodes that involve memory operations.
    short addrRegI;

    //STACK
    //The stack is only used to store return addresses when subroutines are called.
    // The original 1802 version allocated 48 bytes for up to 24 levels of nesting;
    // modern implementations normally have at least 16 levels.
    short[] stack = new short[16];
    short sp; // stack pointer

    //TIMERS
    //CHIP-8 has two timers. They both count down at 60 hertz, until they reach 0.
    //CHIP-8 has two timers. They both count down at 60 hertz, until they reach 0.
     //  Delay timer: This timer is intended to be used for timing the events of games.
    //  Its value can be set and read.
    char delayTimer;

    //Sound timer: This timer is used for sound effects.
    // When its value is nonzero, a beeping sound is made.
    char soundTimer;
}
