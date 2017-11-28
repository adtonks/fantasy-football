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

public class ServerSideOperations extends Thread {
	Lock csvLock;

	public ServerSideOperations(Lock _csvLock) {
		this.csvLock = _csvLock;
		
	}

	
}
