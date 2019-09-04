package com.khoa.emulator;

public class Chip8 {
    // MEMORY
    // The Chip 8 has 4K memory in total, which we can emulated as:
    char memory[] = new char[4096];
    /*
    The Chip 8 has 35 opcodes which are all two bytes long.
    To store the current opcode, we need a data type that allows us to store two bytes.
    */
    short currentOpCode;

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

    // There is an Index register I and a program counter (pc) which can have a value from 0x000 to 0xFFF
    short I;
    short pc;

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

    /*
    The graphics of the Chip 8 are black and white and the screen has a total of 2048 pixels (64 x 32).
    This can easily be implemented using an array that hold the pixel state (1 or 0)
    */
    char[] gfx = new char[64 * 32];

    /*
     HEX based keypad (0x0-0xF), you can use an array to store the current state of the key
    */
    char[] key = new char[16];

    /**
     * <ol>
     *     <li>Fetch Opcode</li>
     *     <li>Decode Opcode</li>
     *     <li>Execute Opcode</li>
     *     <li>Update timers</li>
     * </ol>
     */
    void emulateCycle(){

    }

    /**
     * During this step, the system will fetch one opcode from the memory at the location
     *   specified by the program counter (pc).
     * In our Chip 8 emulator, data is stored in an array in which each address contains one byte.
     * As one opcode is 2 bytes long, we will need to fetch two successive bytes
     *    and merge them to get the actual opcode.
     */
    void fetchOpCode(){
        short opbyte1 = (short) (memory[pc] << 8);
        short opbyte2 = (short) memory[pc+1];
        currentOpCode = (short) (opbyte1 | opbyte2);
    }

    public static void main(String[] args) {
        char pc  = 0xA2;
        char pc1 = 0xF0;
        short op2 = (short)pc1;
        System.out.println(String.format("0x%04X", pc << 8 | op2));
    }
}
