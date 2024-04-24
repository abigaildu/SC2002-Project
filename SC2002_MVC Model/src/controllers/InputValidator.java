package controllers;

public class InputValidator {
	public static boolean isInRange(String input, int start, int end) {
	    try {
	      int number = Integer.parseInt(input);
	      if (number < start || number > end) {
	        System.out
	            .print("Please input number in range " + start + "-" + end + " : ");
	        return false;
	      }
	    } catch (NumberFormatException e) {
	      System.out.print("Please input an integer number: ");
	      return false;
	    }
	    return true;
	  }

	  public static boolean isNotEmptyString(String input) {
	    if (Objects.isNull(input)) {
	      return false;
	    }
	    return input.matches(Constants.STRING_PATTERN);

	  }
	  
	  public static boolean isGender(String input) {
	    if (Objects.isNull(input)) {
	      return false;
	    }
	    return input.matches(Constants.GENDER_PATTERN);

	  }

	  public static boolean isStatus(String status) {
	    if (Objects.isNull(status)) {
	      return false;
	    }
	    return status.matches(Constants.STATUS_PATTERN);

	  }

	  public static String inputString(Scanner scanner) {
	    String input = null;
	    do {
	      input = scanner.nextLine();
	      if (Validate.isNotEmptyString(input)) {
	        return input;
	      }
	      System.out.print("Please input not empty value: ");
	      continue;
	    } while (true);
	  }

	  public static int inputGender(Scanner scanner) {
	    String input = null;
	    do {
	      input = scanner.nextLine();
	      if (Validate.isGender(input)) {
	        
	        return Integer.parseInt(input);
	      }
	      System.out.print("Please input 0 and 1 only: ");
	      continue;
	    } while (true);
	  }
	  
	  public static String inputInRange(Scanner scanner, int start, int end) {
	    String input = null;
	    do {
	      input = scanner.nextLine();
	      if (isInRange(input, start, end)) {
	        return input;
	      }
	        continue;
	      
	    } while (true);
	  }

	  public static int inputInteger(Scanner scanner) {
	    String input;
	    int result;
	    while (true) {
	      input = scanner.nextLine();
	      try {
	        result = Integer.parseInt(input);
	        if (result > 0)
	          return result;
	        else {
	          System.out.print("Please input positive integer: ");
	          continue;
	        }
	      } catch (NumberFormatException e) {
	        System.out.print("Please enter integer number: ");
	        continue;
	      }
	    }
	  }
	  
	  

	  public static String inputStatus(Scanner scanner) {
	    String input;
	    do {
	      input = scanner.nextLine();
	      if (isStatus(input) && isNotEmptyString(input)) {
	        return input;
	      } else {
	        System.out.print("Please input status is active or inactive");
	        continue;
	      }
	    } while (true);
	  }
}
