package pipelineproject3;

public class printOut {

	//Displays the pipeline registers and the 32 registers
	public void Print_out_everything(IFID IFID, IDEX IDEX, EXMEM EXMEM, MEMWB MEMWB, int[] Regs, int[] mem) {
		
		//Pipeline registers
		System.out.println(IFID);
		System.out.println(IDEX);
		System.out.println(EXMEM);
		System.out.println(MEMWB);
		
		//Registers
		System.out.println("Registers: ");
		System.out.println("----------");
		
		for (int i = 0; i < Regs.length; i++){
			System.out.printf("%s%d%s%X\n","$", i , ": 0x",Regs[i]);
		}
		
		System.out.println("\n\n");

	}
	

}
