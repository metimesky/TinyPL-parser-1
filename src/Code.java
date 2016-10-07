import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Code {
	static List<Instruction> byteCode = new ArrayList<>();
	static int byteOffset;

	static List<Character> idNumber = new ArrayList<>();

	public static int gen(Instruction i) {
		byteCode.add(i);
		return byteCode.size() - 1;
	}

	public static List<Instruction> popInstructionsUntil(int byteOffset) {
		List<Instruction> poppedInstructions = new ArrayList<>();

		do {
			final Instruction remove = byteCode.remove(byteCode.size() - 1);
			poppedInstructions.add(remove);
			Code.byteOffset = Code.byteOffset - remove.numBytes;
		} while (byteCode.get(byteCode.size() - 1).byteOffset >= byteOffset);

		Collections.reverse(poppedInstructions);
		return poppedInstructions;
	}

	public static void appendCurrentAddress(int index) {
		final Instruction instruction = byteCode.get(index);
		instruction.instruction = instruction.instruction + byteOffset;
	}

	private static Instruction addInstruction(String line, int numBytes) {
		final int bytes = numBytes + 1;
		final Instruction i = Instruction.instruction(byteOffset, line, bytes);
		byteOffset += bytes;
		return i;
	}

	private static Instruction addInstruction(String line) {
		return addInstruction(line, 0);
	}

	public static Instruction intCode(int i) {
		if (i > 127)
			return addInstruction("sipush " + i, 2);
		if (i > 5)
			return addInstruction("bipush " + i, 1);
		return addInstruction("iconst_" + i);
	}

	public static Instruction end() {
		return addInstruction("return");
	}

	public static Instruction loadId(char id) {
		for (int i = 0; i < idNumber.size(); i++) {
			char character = idNumber.get(i);
			if (character == id) {
				final int j = i + 1;
				if (j > 3)
					return addInstruction("iload " + j, 1);
				return addInstruction("iload_" + j);
			}
		}
		return Instruction.EMPTY_INSTRUCTION;
	}

	public static Instruction storeId(char id) {
		int index = 0;
		boolean exists = false;
		for (int i = 0; i < idNumber.size(); i++) {
			Character character = idNumber.get(i);
			if (character == id) {
				exists = true;
				index = i;
			}
		}
		if (!exists) {
			idNumber.add(id);
		}
		index = index == 0 ? 1 : (index + 1);
		if (index > 3)
			return addInstruction("istore " + index, 1);
		return addInstruction("istore_" + index);
	}

	public static Instruction opCode(char op) {
		switch (op) {
		case '+':
			return addInstruction("iadd");
		case '-':
			return addInstruction("isub");
		case '*':
			return addInstruction("imul");
		case '/':
			return addInstruction("idiv");
		default:
			return Instruction.EMPTY_INSTRUCTION;
		}
	}

	public static Instruction ifPlaceHolder(String op) {
		switch (op) {
		case "<":
			return addInstruction("if_icmpge ", 2);
		case ">":
			return addInstruction("if_icmple ", 2);
		case "==":
			return addInstruction("if_icmpne ", 2);
		case "!=":
			return addInstruction("if_icmpeq ", 2);
		default:
			return Instruction.EMPTY_INSTRUCTION;
		}
	}

	public static Instruction gotoOffset(int byteOffset) {
		return addInstruction("goto " + byteOffset, 2);
	}

	public static Instruction gotoPlaceHolder() {
		return addInstruction("goto ", 2);
	}

	public static void output() {
		byteCode.forEach(System.out::println);
	}

	public static class Instruction {
		public static final Instruction EMPTY_INSTRUCTION = new Instruction(0, 0, "EMPTY");
		int byteOffset;
		int numBytes;
		String instruction;

		private Instruction(int byteOffset, int numBytes, String instruction) {
			this.byteOffset = byteOffset;
			this.numBytes = numBytes;
			this.instruction = instruction;
		}

		public static Instruction instruction(int byteOffset, String instruction, int bytes) {
			return new Instruction(byteOffset, bytes, instruction);
		}

		@Override
		public String toString() {
			return byteOffset + ": " + instruction;
		}
	}
}
