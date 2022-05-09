package pipelineproject3;

public class pipelineSimulator {
	public static void main(String[] args) {

		pipelineSimulator simulator = new pipelineSimulator();
		simulator.dataPath();

	}

	public void dataPath() {
		// creating the memory and initialize it
		int mainmemory = 1024;
		int[] Main_mem = new int[mainmemory];

		int count = 0; // creating the memory and initialize it
		while (count < mainmemory) {
			for (int i = 0; i < 0xff + 1; i++) {
				Main_mem[count] = i;
				// System.out.printf("%d", count);
				// System.out.println("Main Memory[" + String.format("%x", count) + "] = " +
				// String.format("%x", Main_mem[i]));
				count++;
			}
		}

		// Regs
		int[] Regs = new int[32];
		Regs[0] = 0;
		int regValue = 0x100;

		for (int i = 1; i < Regs.length; i++) {
			Regs[i] = regValue + i;
		}

		int[] instruction_cache = { 
				0xA1020000, 
				0x810AFFFC, 
				0x00831820,
				0x01263820,
				0x01224820, 
				0x81180000,	
				0x81510010,
				0x00624022,
				0x00000000, 
				0x00000000, 
				0x00000000,
				0x00000000 };

		// Pipeline Registers
		IFID IFID = new IFID();
		IDEX IDEX = new IDEX();
		EXMEM EXMEM = new EXMEM();
		MEMWB MEMWB = new MEMWB();

		for (int i = 0; i < instruction_cache.length; i++) {
			System.out.println("< Clock Cycle " + (i + 1) + " >");

			IF_stage(instruction_cache[i], IFID);
			ID_stage(IFID, IDEX, Regs);
			EX_stage(IDEX, EXMEM);
			MEM_stage(EXMEM, MEMWB, Main_mem);
			WB_stage(MEMWB, Regs);

			System.out.println("--------------------------------------------------------------------");
			Print_out_everything(IFID, IDEX, EXMEM, MEMWB, Regs, Main_mem);
			Copy_write_to_read(IFID, IDEX, EXMEM, MEMWB);

		}

	}

	// IF Stage------------------------------------------------------------------------------------------------------

	// Fetch instructions and store into the first stage register write value
	public void IF_stage(int instruction, IFID IFID) {
		IFID.writeInstruction(instruction);

	}

	// ID Stage------------------------------------------------------------------------------------------------------

	// Read from IFID pipeline value and decode instruction
	public void ID_stage(IFID IFID, IDEX IDEX, int[] Regs) {
		// Reading instruction
		int instruction = IFID.readInstruction();

		// Nop instruction.
		if (instruction == 0) {
			IDEX.writeRegDst(0);
			IDEX.writeALUSrc(0);
			IDEX.writeALUOp(0b00);
			IDEX.writeMemRead(0);
			IDEX.writeMemWrite(0);
			IDEX.writeMemToReg(0);
			IDEX.writeRegWrite(0);

			IDEX.writeReadReg1Value(0);
			IDEX.writeReadReg2Value(0);
			IDEX.writeSEOffset(0);
			IDEX.writeWriteReg_20_16(0);
			IDEX.writeWriteReg_15_11(0);
			IDEX.writeFunction(0);
		}

		else {

			int opecode = (instruction & 0xFC000000) >>> 26;

			// Registers of test instruction
			int rs;
			int rt;
			int rd;
			int function;
			short offset;

			rs = (instruction & 0x03E00000) >>> 21;
			rt = (instruction & 0x001F0000) >>> 16;
			rd = (instruction & 0x0000F800) >>> 11;
			offset = (short) ((instruction & 0x0000FFFF));
			function = instruction & 0x0000003F;

			// R-format set control signals
			if ((opecode) == 0) {

				IDEX.writeRegDst(1);
				IDEX.writeALUSrc(0);
				IDEX.writeALUOp(0b10);
				IDEX.writeMemRead(0);
				IDEX.writeMemWrite(0);
				IDEX.writeMemToReg(0);
				IDEX.writeRegWrite(1);

			}

			// Load: Set Control Values
			else if (opecode == 0x20) {

				IDEX.writeRegDst(0);
				IDEX.writeALUSrc(1);
				IDEX.writeALUOp(0b00);
				IDEX.writeMemRead(1);
				IDEX.writeMemWrite(0);
				IDEX.writeMemToReg(1);
				IDEX.writeRegWrite(1);

			}

			// Stores set control signals
			else if (opecode == 0x28) {

				IDEX.writeRegDst(0);
				IDEX.writeALUSrc(1);
				IDEX.writeALUOp(0b00);
				IDEX.writeMemRead(0);
				IDEX.writeMemWrite(1);
				IDEX.writeMemToReg(0);
				IDEX.writeRegWrite(0);

			}

			IDEX.writeReadReg1Value(Regs[rs]);
			IDEX.writeReadReg2Value(Regs[rt]);
			IDEX.writeSEOffset((int) offset);
			IDEX.writeWriteReg_20_16(rt);
			IDEX.writeWriteReg_15_11(rd);
			IDEX.writeFunction(function);
		}
	}

	// EX Stage------------------------------------------------------------------------------------------------------
	public void EX_stage(IDEX IDEX, EXMEM EXMEM) {
		EXMEM.writeMemRead(IDEX.readMemRead());
		EXMEM.writeMemWrite(IDEX.readMemWrite());
		EXMEM.writeMemToReg(IDEX.readMemToReg());
		EXMEM.writeRegWrite(IDEX.readRegWrite());

		// Store value is going to be placed in Register 2
		EXMEM.writeSBValue(IDEX.readReadReg2Value());

		if (IDEX.readRegDst() == 0) {
			EXMEM.writeWriteRegNum(IDEX.readWriteReg_20_16());
		}

		else { // R format
			EXMEM.writeWriteRegNum(IDEX.readWriteReg_15_11());
		}

		// ALU COMPONENT

		int upperValue = IDEX.readReadReg1Value();
		int lowerValue;

		// Determining lower value of ALU via ALUSrc
		if (IDEX.readALUSrc() == 0) { 
			lowerValue = IDEX.readReadReg2Value();
		} else { 
			lowerValue = IDEX.readSEOffset();
		}

		// calculate ALUOp
		int function = 0;
		if (IDEX.readALUOp() == 0b00) { // I-Format
			// Load and stores use add
			function = 0x20;
		}

		else if (IDEX.readALUOp() == 0b10) {
			// R-Format
			function = IDEX.readFunction();
		}

		// Calculates ALUResult
		int ALUResult = 0;

		if (function == 0x20) { // add
			ALUResult = upperValue + lowerValue;
		} else if (function == 0x22) {
			ALUResult = upperValue - lowerValue;
		} else if (function == 0x24) { // bitwise and
			ALUResult = (upperValue & lowerValue);
		} else if (function == 0x25) { // bitwise or
			ALUResult = (upperValue | lowerValue);
		} else if (function == 0x2A) {
			if (upperValue < lowerValue) {
				ALUResult = 1;
			} else {
				ALUResult = 0;
			}
		}
		EXMEM.writeALUResult(ALUResult);
	}

	// MEM Stage------------------------------------------------------------------------------------------------------
	public void MEM_stage(EXMEM EXMEM, MEMWB MEMWB, int[] mem) {
		// Passing the values
		MEMWB.writeALUResult(EXMEM.readALUResult());
		MEMWB.writeMemToReg(EXMEM.readMemToReg());
		MEMWB.writeRegWrite(EXMEM.readRegWrite());
		MEMWB.writeWriteRegNum(EXMEM.readWriteRegNum());

		// Need to read from the mainmemory for the load
		if (EXMEM.readMemRead() == 1) {
			// ALUResult is a calculated address
			MEMWB.writeLBDataValue(mem[EXMEM.readALUResult()]);
		}

		// need to write to the main memory if it is a store
		else if (EXMEM.readMemWrite() == 1) {
			mem[EXMEM.readALUResult()] = EXMEM.readSBValue();
		}

	}

	// WB Stage------------------------------------------------------------------------------------------------------
	public void WB_stage(MEMWB MEMWB, int[] Regs) {

		// from the main memory write to the register
		if (MEMWB.readRegWrite() == 1) {

			// if it is a load, it is going to be written in the register
			if (MEMWB.readMemToReg() == 1) {
				Regs[MEMWB.readWriteRegNum()] = MEMWB.readLBDataValue();
			}

			// if it is a r format, the alu result is going to be written in the register.
			else {
				Regs[MEMWB.readWriteRegNum()] = MEMWB.readALUResult();
			}

		}
	}

	public void Print_out_everything(IFID IFID, IDEX IDEX, EXMEM EXMEM, MEMWB MEMWB, int[] Regs, int[] mem) {

		// Pipeline registers
		System.out.println(IFID);
		System.out.println(IDEX);
		System.out.println(EXMEM);
		System.out.println(MEMWB);

		// Registers
		System.out.println("Registers: ");
		System.out.println("----------");

		for (int i = 0; i < Regs.length; i++) {
			System.out.printf("%s%d%s%X\n", "$", i, ": 0x", Regs[i]);
		}

		System.out.println("\n\n");

	}

	// Copy all the data from the write side to the read side
	public void Copy_write_to_read(IFID IFID, IDEX IDEX, EXMEM EXMEM, MEMWB MEMWB) {
		IFID.copyWriteToRead();
		IDEX.copyWriteToRead();
		EXMEM.copyWriteToRead();
		MEMWB.copyWriteToRead();

	}

}
