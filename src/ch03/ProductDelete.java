package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDelete {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/shop2?severTimezone=Asia/Seoul";
        String user = System.getenv("DB_USER");
        String pwd = System.getenv("DB_PASSWORD");

        // UPDATE 쿼리 구문을 미리 String으로 만들어두자
        // 텍스트블록 문법 JDK 13이후 사용 가능
        String sql = """
                    DELETE FROM product2 WHERE id = ?
                """;

        int targetId = 2;

        try (Connection conn = DriverManager.getConnection(url, user, pwd);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("===== 전체 상품 목록 조히 =====");
            pstmt.setInt(1, targetId);
            int rows = pstmt.executeUpdate();

            System.out.println("상품 삭제: 타겟상품 ID: " + targetId);

            if(rows > 0) {
                System.out.println(rows + "개의 상품이 삭제되었습니다.");
            } else {
                System.out.println("삭제할 상품을 찾지 못했습니다.");
            }

        } catch (SQLException e) {
            System.out.println("오류: " + e.getMessage());
        }
    } // end of main

} // end of class
