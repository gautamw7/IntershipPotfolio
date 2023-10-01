package Project;

import Project.DatabaseConnection;
import java.sql.*;
import java.util.*;

public class ALP {
	private DatabaseConnection dbConnection;
	String[] languages = {"Android_Java_Kotlin", "C", "Cpp", "Rust", "Go", "iOS_Swift_Objective_C", "Java_Non_Android", "JavaScript",
			"TypeScript", "Julia", "PHP", "Python", "Ruby", "codeSQL", "Haskell", "OCaml"
	};
	List<DataObject> criteriaList = new ArrayList<>();
	List<DataObject> alternativeList = new ArrayList<>();
	
	void input_alternatives() {
		try {
			openDatabaseConnection();
			Connection conn = dbConnection.getConnection();
			
			String command = "SELECT * FROM user_languages";
			
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(command);
			
			
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				int[] alternatives = new int[16];
				
				for(int i=0; i<16; i++) {
					alternatives[i] = rs.getInt(languages[i]);
				}
				
				DataObject d = new DataObject(id, username, alternatives);
				alternativeList.add(d);
			}
			
			for(DataObject d : alternativeList) {
				System.out.println(d);
			}
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			closeDatabaseConnection();
		}
		
	}
	
	void input_criteria() {
		try {
			openDatabaseConnection();
			Connection conn = dbConnection.getConnection();
			
			String command = "SELECT * FROM employer_preferences";
			
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(command);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				int[] criteria = new int[16];
				
				for(int i=0; i<16; i++) {
					criteria[i] = rs.getInt(languages[i]);
				}
				
				DataObject d = new DataObject(id, username, criteria);
				criteriaList.add(d);
			}
			
			for(DataObject d : criteriaList) {
				System.out.println(d);
			}
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			closeDatabaseConnection();
		}
	}
	
	private void openDatabaseConnection(){
        dbConnection = new DatabaseConnection();
    }
	
	private void closeDatabaseConnection() {
        dbConnection.closeConnection();
    }
	
	void algo() {
		try {            
            double[][] criteriaPairwiseMatrix = new double[16][16];

            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    criteriaPairwiseMatrix[i][j] = (criteriaList.get(0).prefArr[i])/(criteriaList.get(0).prefArr[j]);
                }
            }

            double[][] criteriaNormalizedMatrix = new double[16][16];
            for (int i = 0; i < 16; i++) {
                double sum = 0;
                for (int j = 0; j < 16; j++) {
                    sum += criteriaPairwiseMatrix[i][j];
                }
                for (int j = 0; j < 16; j++) {
                    criteriaNormalizedMatrix[i][j] = criteriaPairwiseMatrix[i][j] / sum;
                }
            }

            double[] criteriaWeights = new double[16];
            for (int i = 0; i < 16; i++) {
                double product = 1;
                for (int j = 0; j < 16; j++) {
                    product *= criteriaNormalizedMatrix[j][i];
                }
                criteriaWeights[i] = Math.pow(product, 1.0 / 16);
            }

            double[] alternativeScores = new double[16];
            for (int i = 0; i < 2; i++) {
                double score = 0;
                for (int j = 0; j < 16; j++) {
                    double criterionScore = alternativeList.get(i).prefArr[j];
                    score += criterionScore * criteriaWeights[j];
                }
                alternativeScores[i] = score;
            }

            System.out.println("\nPerformance scores of alternatives:");
            for (int i = 0; i < 2; i++) {
                System.out.println("Alternative " + (i + 1) + ": " + alternativeScores[i]);
            }
        } catch(Exception e) {
        	System.out.println(e);
        }
	}
	
	public static void main(String[] args) {
		ALP a = new ALP();
		a.input_alternatives();
		a.input_criteria();
		a.algo();
	}
	
	static class DataObject {
        private int id;
        private String username;
        private int[] prefArr;

        public DataObject(int id, String username, int[] prefArr) {
            this.id = id;
            this.username = username;
            this.prefArr = prefArr;
        }
        
        @Override
        public String toString() {
            String s = "DataObject{" +
                    "id=" + id +
                    ", username=" + username + ", pref=";
            
            for(int i=0; i<16; i++) {
            	s = s+prefArr[i]+",";
            }
            
            return s;
        }
    }

}


