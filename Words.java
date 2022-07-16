import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Words {

    public static boolean verifyLength(String guess) {
        return guess.length() == 5;
    }

    public static boolean checkIfUsed(String[] attempted, String guess) {
        for (String word : attempted) {
            if (guess.equals(word)) return true;
        }
        return false;
    }

    public static String getChar(String word, int index) {
        return word.substring(index, index + 1);
    }

    public static String substituteChar(String str, String characterIns, int index) {
        String frontString = str.substring(0, index);
        String backString = str.substring(index + 1);
        return frontString + characterIns + backString;
    }

    public static ArrayList<String> getUniqueLetters(String word) {
        ArrayList<String> allLetters = new ArrayList<String>(0);

        for (int i = 0; i < word.length(); i++) {
            // if the letter is already in the array, delete it
            for (int j = 0; j < allLetters.size(); j++) {
                if (allLetters.get(j).equals(getChar(word, i))) {
                    allLetters.remove(j);
                    j--;
                }
            }
            allLetters.add(getChar(word, i));
        }
        return allLetters;
    }

    public static ArrayList<String> removeDuplicatesFromArr(ArrayList<String> aList) {
        List<String> newList = aList.stream().distinct().collect(Collectors.toList());
        return new ArrayList<String>(newList);
    }

    public static ArrayList<String> stringToArray(String str) {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < str.length(); i++) {
            result.add(str.substring(i, i + 1));
        }
        return result;
    }

    public static String getGreenLetters(String guess, String answer) {
        String result = "_____";

        for (int index = 0; index < guess.length(); index++) {
            if (getChar(guess, index).equals(getChar(answer, index)))
                result = substituteChar(result, getChar(answer, index), index);
        }
        return result;
    }

    // uses green letters, finds the similar letters and removes the ones that were direct matches
    public static String getYellowLetters(String guess, String answer) {
        String greenStr = getGreenLetters(guess, answer);
        ArrayList<String> greenLetters = new ArrayList<String>();
        ArrayList<Integer> usedMatchesYlw = new ArrayList<Integer>();
        String result = "_____";

        // adds all green letters to an array
        for (int i = 0; i < guess.length(); i++) {
            if (!getChar(greenStr, i).equals("_"))
                greenLetters.add(getChar(greenStr, i));
        }

        // searches all matches
        for (int guessIndex = 0; guessIndex < guess.length(); guessIndex++) {
            for (int ansIndex = 0; ansIndex < answer.length(); ansIndex++) {
                if (!usedMatchesYlw.contains(ansIndex)) {
                    if (getChar(guess, guessIndex).equals(getChar(answer, ansIndex))) {
                        // check if the letter is in the green letters, if not then subChar
                        if (!greenLetters.contains(getChar(guess, guessIndex)))
                            result = substituteChar(result, getChar(guess, guessIndex), guessIndex);
                        else {
                            greenLetters.remove(getChar(guess, guessIndex));
                        }
                        usedMatchesYlw.add(ansIndex);
                    }
                }
            }
        }

        return result;
    }

    public static String getColorData(String guess, String answer) {
        String result = "_____";
        String grnString = getGreenLetters(guess, answer);
        String ylwString = getYellowLetters(guess, answer);

        for (int i = 0; i < answer.length(); i++) {
            if (!getChar(ylwString, i).equals("_"))
                result = substituteChar(result, "Y", i);
            if (!getChar(grnString, i).equals("_"))
                result = substituteChar(result, "G", i);
        }
        return result;
    }
}
