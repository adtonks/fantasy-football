package server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import clientObjects.Positions;
import exceptions.PlayerNotFound;
import exceptions.ResultsReadError;

/**
 * This class extends the Thread class to manage the CSV file
 * @author charisannelim
 *
 */
public class ServerSideOperations extends Thread {
	Lock csvLock;
	
	/**
	 * Takes in an instance of the Lock class
	 * @param _csvLock
	 */
	public ServerSideOperations(Lock _csvLock) {
		this.csvLock = _csvLock;
		
	}

	
}
