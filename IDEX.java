package pipelineproject3;

public class IDEX {
	
	// Write the values from IDEX
	// Control Signals
	private int writeRegDst = 0;
	private int writeALUSrc = 0;
	private int writeALUOp = 0;
	private int writeMemRead = 0;
	private int writeMemWrite = 0;
	private int writeMemToReg = 0;
	private int writeRegWrite = 0;
	// Instruction Values
	private int writeReadReg1Value = 0;
	private int writeReadReg2Value = 0;
	private int writeSEOffset = 0;
	private int writeWriteReg_20_16 = 0;
	private int writeWriteReg_15_11 = 0;
	private int writeFunction = 0;

	// Read values of the IDEXreg
	// Control Signals
	private int readRegDst = 0;
	private int readALUSrc = 0;
	private int readALUOp = 0;
	private int readMemRead = 0;
	private int readMemWrite = 0;
	private int readMemToReg = 0;
	private int readRegWrite = 0;
	// Instruction Values
	private int readReadReg1Value = 0;
	private int readReadReg2Value = 0;
	private int readSEOffset = 0;
	private int readWriteReg_20_16 = 0;
	private int readWriteReg_15_11 = 0;
	private int readFunction = 0;

	// The setter Methods:

	// Control Signals
	public void writeRegDst(int writeRegDst) {
		this.writeRegDst = writeRegDst;
	}

	public void writeALUSrc(int writeALUSrc) {
		this.writeALUSrc = writeALUSrc;
	}

	public void writeALUOp(int writeALUOp) {
		this.writeALUOp = writeALUOp;
	}

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

	public void writeReadReg1Value(int writeReadReg1Value) {
		this.writeReadReg1Value = writeReadReg1Value;
	}

	public void writeReadReg2Value(int writeReadReg2Value) {
		this.writeReadReg2Value = writeReadReg2Value;
	}

	public void writeSEOffset(int writeSEOffset) {
		this.writeSEOffset = writeSEOffset;
	}

	public void writeWriteReg_20_16(int writeWriteReg_20_16) {
		this.writeWriteReg_20_16 = writeWriteReg_20_16;
	}

	public void writeWriteReg_15_11(int writeWriteReg_15_11) {
		this.writeWriteReg_15_11 = writeWriteReg_15_11;
	}

	public void writeFunction(int writeFunction) {
		this.writeFunction = writeFunction;
	}

	// GETTER METHODS

	public int readRegDst() {
		return readRegDst;
	}

	public int readALUSrc() {
		return readALUSrc;
	}

	public int readALUOp() {
		return readALUOp;
	}

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

	public int readReadReg1Value() {
		return readReadReg1Value;
	}

	public int readReadReg2Value() {
		return readReadReg2Value;
	}

	public int readSEOffset() {
		return readSEOffset;
	}

	public int readWriteReg_20_16() {
		return readWriteReg_20_16;
	}

	public int readWriteReg_15_11() {
		return readWriteReg_15_11;
	}

	public int readFunction() {
		return readFunction;
	}

	// COPY WRITE TO READ
	public void copyWriteToRead() {

		readRegDst = writeRegDst;
		readALUSrc = writeALUSrc;
		readALUOp = writeALUOp;
		readMemRead = writeMemRead;
		readMemWrite = writeMemWrite;
		readMemToReg = writeMemToReg;
		readRegWrite = writeRegWrite;

		readReadReg1Value = writeReadReg1Value;
		readReadReg2Value = writeReadReg2Value;
		readSEOffset = writeSEOffset;
		readWriteReg_20_16 = writeWriteReg_20_16;
		readWriteReg_15_11 = writeWriteReg_15_11;
		readFunction = writeFunction;

	}

	// Print statement
	public String toString() {

		String print = "";

		print += "ID/EX WRITE (written to by the ID stage):\n";
		print += "     Control Signals:\n";
		print += "         RegDst   = " + writeRegDst + "\n";
		print += "         ALUSrc   = " + writeALUSrc + "\n";
		print += "         ALUOp    = 0b" + Integer.toBinaryString(writeALUOp) + "\n";
		print += "         MemRead  = " + writeMemRead + "\n";
		print += "         MemWrite = " + writeMemWrite + "\n";
		print += "         MemToReg = " + writeMemToReg + "\n";
		print += "         RegWrite = " + writeRegWrite + "\n";
		print += String.format("%s0x%08X\n", "     ReadReg1Value  = ", writeReadReg1Value);
		print += String.format("%s0x%08X\n", "     ReadReg2Value  = ", writeReadReg2Value);
		print += String.format("%s0x%08X\n", "     SE Offset 	    = ", writeSEOffset);
		print += "     WriteReg_20_16 = " + writeWriteReg_20_16 + "\n";
		print += "     WriteReg_15_11 = " + writeWriteReg_15_11 + "\n";
		print += String.format("%s0x%02X\n\n", "     Function       = ", writeFunction);

		print += "ID/EX READ (read by the EX stage):\n";
		print += "     Control Signals:\n";
		print += "         RegDst   = " + readRegDst + "\n";
		print += "         ALUSrc   = " + readALUSrc + "\n";
		print += "         ALUOp    = 0b" + Integer.toBinaryString(readALUOp) + "\n";
		print += "         MemRead  = " + readMemRead + "\n";
		print += "         MemWrite = " + readMemWrite + "\n";
		print += "         MemToReg = " + readMemToReg + "\n";
		print += "         RegWrite = " + readRegWrite + "\n";
		print += String.format("%s0x%08X\n", "     ReadReg1Value  = ", readReadReg1Value);
		print += String.format("%s0x%08X\n", "     ReadReg2Value  = ", readReadReg2Value);
		print += String.format("%s0x%08X\n", "     SE Offset 	    = ", readSEOffset);
		print += "     WriteReg_20_16 = " + readWriteReg_20_16 + "\n";
		print += "     WriteReg_15_11 = " + readWriteReg_15_11 + "\n";
		print += String.format("%s0x%02X\n\n", "     Function       = ", readFunction);

		return print;
	}

}
