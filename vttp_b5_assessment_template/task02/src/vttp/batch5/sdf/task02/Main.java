package vttp.batch5.sdf.task02;

import java.io.File;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {

		if (args.length!=1){
            System.out.println ("Please upload a valid file to process");
            System.exit(0);
        }

        File f = new File(args[0]);
        if (!(f.exists())){
            System.out.println("File does not exist in your specified path. Please Upload a valid file");
            System.exit(0);
        }
        if (!(f.isFile())){
            System.out.println("The path that you have specified does not correspond to a file. Please upload the path of a valid file");
            System.exit(0);
		}
		String [][]board = new String[3][3];
		System.out.printf("Processing: %s\n\n", args[0]);
		System.out.println("Board:");
		board=Methods.printBoard(f);
		List<String> positions = Methods.getLegalPositions(board);
		for(String position:positions){
			System.out.println(position);
		}
	}
}
