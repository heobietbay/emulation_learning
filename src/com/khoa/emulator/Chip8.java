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
        fetchOpCode();
    }

    /**
     * During this step, the system will fetch one opcode from the memory at the location
     *   specified by the program counter (pc).
     * In our Chip 8 emulator, data is stored in an array in which each address contains one byte.
     * As one opcode is 2 bytes long, we will need to fetch two successive bytes
     *    and merge them to get the actual opcode.
     */
    void fetchOpCode(){
        currentOpCode = merge2CharBigEndian(memory[pc],memory[pc+1]);
    }

    void decodeOpcode(){
        // In the opcode table (Chip8_ReadMe) , we can see opcode and what it means
        // Suppose we have opcode  0xA2F0 -> A2F0
        // In the table, we have something similar: ANNN, which means "Sets I to the address NNN"
        // A2F0 -> set I to the address 2F0 -> I = 2F0
        // to extract the 2F0  part, we can use AND(&) operator, with 0FFF
       //  BECAUSE:
        // A2F0 -> in binary would be ‭1010 0010 1111 0000‬
        // 0FFF -> in binary would be 0000 1111 1111 1111
        // AND result:                0000 0010 1111 0000

        //Similarly, to get the initial opcode, we can AND the currentOpcode with F000
        //EX: A2F0 -> 1010 0010 1111 0000‬
        //    F000 -> 1111 0000 0000 0000
        //AND      -> 1010 0000 0000 0000
    }

    void executeOpcode(){
        short decodeOp =  (short) (currentOpCode & 0xF000);
        switch (decodeOp){
            case (short) 0xA000: // ANNN: Sets I to the address NNN
                I = (short) (currentOpCode & 0x0FFF);
                pc += 2;
                break;
            default: break;
        }
    }

    static short merge2CharBigEndian(char byte1, char byte2){
        short sbyte1 = (short) byte1;
        sbyte1 = (short) (sbyte1 << 8);
        short sbyte2 = (short) byte2;
        return (short) (sbyte1 | sbyte2);
    }

    public static void main(String[] args) {
        char pc  = 0xA2;
        char pc1 = 0xF0;
        System.out.println(String.format("0x%04X", merge2CharBigEndian(pc,pc1)));
    }
}
