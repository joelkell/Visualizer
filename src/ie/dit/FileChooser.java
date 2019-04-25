/** 
  * Enables user to select song from anywhere on their computer
  * Only allows the selection of mp3 Files
  * Returns the Path of the selected file
  */

package ie.dit;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class FileChooser extends JFrame { 
    
    private static final long serialVersionUID = 1L;

	private String path; 
	FileChooser() 
	{ 
	} 

    //selects file from somewhere on computer and stores in path variable
	public void chooseFile() 
	{ 
        JFileChooser fileWindow = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        fileWindow.setAcceptAllFileFilterUsed(false); //Does not allow all files to be selected 
        fileWindow.setDialogTitle("Select an .mp3 file"); //String for Window Title
        FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .mp3 files", "mp3"); //Allow user to only select mp3 files
        fileWindow.addChoosableFileFilter(restrict); 

        int r = fileWindow.showOpenDialog(null); 
        if (r == JFileChooser.APPROVE_OPTION) // if the user selects a file  
        { 
            path = fileWindow.getSelectedFile().getAbsolutePath(); //sets path to path of selected file
        }
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

}