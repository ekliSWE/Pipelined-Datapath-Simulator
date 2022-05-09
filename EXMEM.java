package pipelineproject3;

public class EXMEM {
	// Write values of the IDEX
	// Control Signals
	private int writeMemRead = 0;
	private int writeMemWrite = 0;
	private int writeMemToReg = 0;
	private int writeRegWrite = 0;

	// Instruction Values
	private int writeALUResult = 0;
	private int writeSBValue = 0;
	private int writeWriteRegNum = 0;

	// Read values of the IDEX
	// Control Signals
	private int readMemRead = 0;
	private int readMemWrite = 0;
	private int readMemToReg = 0;
	private int readRegWrite = 0;
	// Instruction Values
	private int readALUResult = 0;
	private int readSBValue = 0;
	private int readWriteRegNum = 0;

	// SETTER METHODS:

	// Control Signals
	public void writeMemRead(int writeMemRead) {
		this.writeMemRead = writeMemRead;
	}

	public void writeMemWrite(int writeMemWrite) {
		this.writeMemWrite = writeMemWrite;
	}

	public void writeMemToReg(int writeMemToReg) {
		this.writeMemToReg = writeMemToReg;
	}

	public void writeRegWrite(int writeRegWrite) {
		this.writeRegWrite = writeRegWrite;
	}

	// Register Values
	public void writeALUResult(int writeALUResult) {
		this.writeALUResult = writeALUResult;
	}

	public void writeSBValue(int writeSBValue) {
		this.writeSBValue = writeSBValue;
	}

	public void writeWriteRegNum(int writeWriteRegNum) {
		this.writeWriteRegNum = writeWriteRegNum;
	}

	// COPY WRITE TO READ
	public void copyWriteToRead() {

		readMemRead = writeMemRead;
		readMemWrite = writeMemWrite;
		readMemToReg = writeMemToReg;
		readRegWrite = writeRegWrite;

		readSBValue = writeSBValue;
		readWriteRegNum = writeWriteRegNum;
		readALUResult = writeALUResult;

	}

	// GETTER METHODS

	public int readMemRead() {
		return readMemRead;
	}

	public int readMemWrite() {
		return readMemWrite;
	}

	public int readMemToReg() {
		return readMemToReg;
	}

	public int readRegWrite() {
		return readRegWrite;
	}

	public int readALUResult() {
		return readALUResult;
	}

	public int readSBValue() {
		return readSBValue;
	}

	public int readWriteRegNum() {
		return readWriteRegNum;
	}

	// Print statement
	public String toString() {

		String print = "";

		print += "EX/MEM WRITE (written to by the EX stage):\n";
		print += "     Control Signals:\n";
		print += "       MemRead  = " + writeMemRead + "\n";
		print += "       MemWrite = " + writeMemWrite + "\n";
		print += "       MemToReg = " + writeMemToReg + "\n";
		print += "       RegWrite = " + writeRegWrite + "\n";
		print += String.format("%s0x%08X\n", "     ALUResult   = ", writeALUResult);
		print += String.format("%s0x%08X\n", "     SBValue     = ", writeSBValue);
		print += "     WriteRegNum = " + writeWriteRegNum + "\n\n";

		print += "EX/MEM READ (read by the MEM stage):\n";
		print += "     Control Signals:\n";
		print += "         MemRead  = " + readMemRead + "\n";
		print += "         MemWrite = " + readMemWrite + "\n";
		print += "         MemToReg = " + readMemToReg + "\n";
		print += "         RegWrite = " + readRegWrite + "\n";
		print += String.format("%s0x%08X\n", "     ALUResult   = ", readALUResult);
		print += String.format("%s0x%08X\n", "     SBValue     = ", readSBValue);
		print += "     WriteRegNum = " + readWriteRegNum + "\n\n";

		return print;
	}

}
