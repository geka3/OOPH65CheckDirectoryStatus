import java.io.File;
import java.util.Arrays;

public class CheckChangeInDirectory implements Runnable {
	File[] prevFiles;
	File[] newFiles;
	File[] prevTemp;

	File dir = null;

	public CheckChangeInDirectory(String dir) {
		super();
		this.dir = new File(dir);

		this.prevFiles = this.dir.listFiles();

		// newFiles = fileArrayCopy(prevFiles);
		newFiles = prevFiles.clone();
		// newFiles = new File[prevFiles.length];
		// System.arraycopy(this.prevFiles, 0, newFiles, 0,
		// this.prevFiles.length);

		Thread th = new Thread(this);
		th.start();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		for (;;) {

			// prevTemp = prevFiles.clone();
			// copy for process
			// prevTemp = fileArrayCopy(prevFiles);
			prevTemp = prevFiles.clone();
			// prevTemp = new File[prevFiles.length];
			// System.arraycopy(prevFiles, 0, prevTemp, 0, prevFiles.length);

			// get new array file list
			newFiles = dir.listFiles();
			//

			// for next time
			prevFiles = dir.listFiles();

			for (int i = 0; i < newFiles.length; i++) {
				for (int j = 0; j < prevTemp.length; j++) {
					if (newFiles[i] != null && prevTemp[j] != null
							&& newFiles[i].getName().equals(prevTemp[j].getName())) {
						newFiles[i] = null;
						prevTemp[j] = null;
					}
				}

			}

			// find new Files
			for (int i = 0; i < newFiles.length; i++) {
				if (newFiles[i] != null && newFiles[i].isDirectory()) {
					System.out.println("new directory: " + newFiles[i].getName());
				} else if (newFiles[i] != null && newFiles[i].isFile()) {
					System.out.println("new file: " + newFiles[i].getName());
				}
			}

			// find deleted files
			for (int i = 0; i < prevTemp.length; i++) {
				if (prevTemp[i] != null) {
					System.out.println("del: " + prevTemp[i].getName());
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public File[] fileArrayCopy(File[] fileArray) {
		File[] newArray = new File[fileArray.length];
		for (int i = 0; i < fileArray.length; i++) {
			newArray[i] = new File((fileArray[i]).getAbsolutePath());
		}
		return newArray;
	}

}
