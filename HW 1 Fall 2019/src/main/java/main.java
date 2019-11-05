/**
 * CSCI 333: Algorithms and Data Structures.
 * Homework 1: Recursion.
 * 
 * Implements two arrays of ordered integers, then uses the trinarySearch
 * function to search for specified integers, testing a variety of use cases.
 * 
 * @author Jonathan Eatroff
 * @since 8/26/19
 */

class HW1
{	
	/**
	 * Implements the arrays and searches for integers within them.
	 * 
	 * @param args does nothing for purposes of this program
	 */
	public static void main(String[] args) {
		int[] array1 = new int[100];
		for(int i = 0; i <= 99; i++) {
			array1[i] = i+1;
		}
		int[] array2 = {1, 5, 29, 29, 29, 34, 102, 103, 203, 593, 593, 1274};
		
		System.out.print("Location (1): " + trinarySearch(array1, 1, 0, 99) + "\n");
		System.out.print("Location (100): " + trinarySearch(array1, 100, 0, 99) + "\n");
		System.out.print("Location (33): " + trinarySearch(array1, 33, 0, 99) + "\n");
		System.out.print("Location (67): " + trinarySearch(array1, 67, 0, 99) + "\n");
		
		System.out.print("Location (1st 29): " + trinarySearch(array2, 29, 0, 11) + "\n");
		System.out.print("Location (1st 593): " + trinarySearch(array2, 593, 0, 11) + "\n");
		System.out.print("Location (203): " + trinarySearch(array2, 203, 0, 11) + "\n");
		System.out.print("Location (62 - not in list): " + trinarySearch(array2, 62, 0, 11) + "\n");
	}
	
	/**
	 * Performs a recursive trinary search for the earliest instance of a given 
	 * integer in a list of integers
	 * 
	 * Assumes the list is non-empty and ordered from smallest to largest
	 * 
	 * @param searchArray the total list of elements to be searched
	 * @param searchValue the value to search for in the list
	 * @param startIndex  the index of the list in which to start searching
	 * @param stopIndex   the index of the list in which to stop searching
	 * @return            the index at which the int was found, or -1 if it was not
	 */
	public static int trinarySearch(int[] searchArray, int searchValue, int startIndex, int stopIndex)
	{
		if (stopIndex-startIndex <= 1) {
			if (searchArray[startIndex] == searchValue)
				return startIndex;
			else if (searchArray[stopIndex] == searchValue)
				return stopIndex;
			else
				return -1;
		}
		
		/**
		 * The index dividing the first and second thirds 
		 */
		int lowThird = lowerThird(stopIndex-startIndex+1) + startIndex;
		/**
		 * The index dividing the second and final thirds 
		 */
		int upThird = upperThird(stopIndex-startIndex+1) + startIndex;
		
		if(searchValue < searchArray[lowThird]) { // check if searchValue is in first third
			return trinarySearch(searchArray, searchValue, startIndex, lowThird-1);
		} else if (searchValue == searchArray[lowThird]) { // check if at border of first & second third
			if(trinarySearch(searchArray, searchValue, startIndex, lowThird-1) == -1)
				return lowThird;
			else
				return trinarySearch(searchArray, searchValue, startIndex, lowThird-1);
		} else if (searchValue < searchArray[upThird]) { // check if searchValue is in second third
			return trinarySearch(searchArray, searchValue, lowThird+1, upThird-1);
		} else if (searchValue == searchArray[upThird]) { // check if at border of second & final third
			if (trinarySearch(searchArray, searchValue, lowThird+1, upThird-1) == -1)
				return upThird;
			else // check for searchValue in last third
				return trinarySearch(searchArray, searchValue, lowThird+1, upThird-1);
		} else {
			return trinarySearch(searchArray, searchValue, upThird+1, stopIndex);
		}
	}
	
/**
 * Shows the first index to divide a list into thirds
 * 
 * The value must be shifted based on the array index at which the search begins
 * 
 * @param size the total number of elements in the list to be trinary searched
 * @return     the index to use as the border of the first and second thirds
 */
	public static int lowerThird(int size) {
		if (size == 1) { // 1 is calculated separately, as the formula below would give -1
			return 0;
		}
		if (size % 3 == 2)
			size += 1;
		size = size/3-1;
		return size;
	}
	
/**
 * Shows the second index to divide a list into thirds
 * 
 * The value must be shifted based on the array index at which the search begins
 * 
 * @param size the total number of elements in the list to be trinary searched
 * @return     the index to use as the border of the second and lasts thirds
 */
	public static int upperThird(int size) {
		if (size%3 == 1) {
			size = 2*(size/3);
			return size;
		}
		if(size%3 == 2)
			size += 1;
		size = 2*(size/3)-1;
		return size;
	}
}