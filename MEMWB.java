package pipelineproject3;

public class MEMWB {
	// Write values of the IDEX
	// Control Signals
	private int writeMemToReg = 0;
	private int writeRegWrite = 0;
	// Instruction Values
	private int writeALUResult = 0;
	private int writeLBDataValue = 0;
	private int writeWriteRegNum = 0;

	// Read values of the IDEX
	// Control Signals
	private int readMemToReg = 0;
	private int readRegWrite = 0;
	// Instruction Values
	private int readALUResult = 0;
	private int readLBDataVlaue = 0;
	private int readWriteRegNum = 0;

	// SETTER METHODS:

	// Control Signals

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

	public void writeLBDataValue(int writeLBDataValue) {
		this.writeLBDataValue = writeLBDataValue;
	}

	public void writeWriteRegNum(int writeWriteRegNum) {
		this.writeWriteRegNum = writeWriteRegNum;
	}

	// The getter method

	public int readMemToReg() {
		return readMemToReg;
	}

	public int readRegWrite() {
		return readRegWrite;
	}

	public int readALUResult() {
		return readALUResult;
	}

	public int readLBDataValue() {
		return readLBDataVlaue;
	}

	public int readWriteRegNum() {
		return readWriteRegNum;
	}

	// COPY WRITE TO READ
	public void copyWriteToRead() {
		readMemToReg = writeMemToReg;
		readRegWrite = writeRegWrite;
		readLBDataVlaue = writeLBDataValue;
		readWriteRegNum = writeWriteRegNum;
		readALUResult = writeALUResult;

	}

	public String toString() {

		String print = "";

		print += "MEM/WB WRITE (written to by the MEM stage):\n";
		print += "     Control Signals:\n";
		print += "         MemToReg = " + writeMemToReg + "\n";
		print += "         RegWrite = " + writeRegWrite + "\n";
		print += String.format("%s0x%08X\n", "     ALUResult   = ", writeALUResult);
		print += String.format("%s0x%08X\n", "     LBDataValue = ", writeLBDataValue);
		print += "     WriteRegNum = " + writeWriteRegNum + "\n\n";

		print += "MEM/WB READ (read by the WB stage):\n";
		print += "     Control Signals:\n";
		print += "         MemToReg = " + readMemToReg + "\n";
		print += "         RegWrite = " + readRegWrite + "\n";
		print += String.format("%s0x%08X\n", "     ALUResult   = ", readALUResult);
		print += String.format("%s0x%08X\n", "     LBDataValue = ", readLBDataVlaue);
		print += "     WriteRegNum = " + readWriteRegNum + "\n\n";

		return print;
	}

}
