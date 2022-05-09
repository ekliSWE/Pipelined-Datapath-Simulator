package pipelineproject3;

public class IFID {
	private int writeInstructionValue = 0;
	private int readInstructionValue = 0;

	// The setter Methos
	private String decodeWriteInstructionValue = "";

	public void writeInstruction(int instruction) {
		writeInstructionValue = instruction;
		decodeWriteInstructionValue = MIPSdecode(instruction);
	}

	// GETTER METHOD
	public int readInstruction() {
		return readInstructionValue;
	}

	public String MIPSdecode(int instruction) {

		int opecode = (instruction & 0xFC000000) >>> 26;

		int rs;
		int rt;
		int rd;

		int function;
		short offset;

		String code = "";

		if ((opecode) == 0) { // R-format

			rs = (instruction & 0x03E00000) >>> 21;
			rt = (instruction & 0x001F0000) >>> 16;
			rd = (instruction & 0x0000F800) >>> 11;

			function = instruction & 0x0000003F;

			if (function == 0x20) {
				code += "add";
			} else if (function == 0x22) {
				code += "sub";
			} else if (function == 0x24) {
				code += "and";
			} else if (function == 0x25) {
				code += "or ";
			} else if (function == 0x2A) {
				code += "slt";
			} else {
				System.out.print(" Instruction was not found ");
			}

			code += " $" + rd + ", $" + rs + ", $" + rt;
		}

		else { // I-format

			rs = (instruction & 0x03E00000) >>> 21;
			rt = (instruction & 0x001F0000) >>> 16;
			offset = (short) ((instruction & 0x0000FFFF));

			// Load
			if (opecode == 0x20) {
				code += "lw  $" + rt + ", " + offset + "($" + rs + ")";
			}

			// Store
			else if (opecode == 0x28) {
				code += "sw  $" + rt + ", " + offset + "($" + rs + ")";
			}

		}

		return code;
	}

	public void copyWriteToRead() {
		readInstructionValue = writeInstructionValue;
	}

	public String toString() {

		String print = "";

		print += "IF/ID WRITE (written to by the IF stage):\n";
		print += String.format("%s0x%08X", "     Inst = ", writeInstructionValue);
		print += "  [" + decodeWriteInstructionValue + "]\n\n";

		print += "IF/ID READ (read by the ID stage):\n";
		print += String.format("%s0x%08X\n\n", "     Inst = ", readInstructionValue);

		return print;

	}

}
