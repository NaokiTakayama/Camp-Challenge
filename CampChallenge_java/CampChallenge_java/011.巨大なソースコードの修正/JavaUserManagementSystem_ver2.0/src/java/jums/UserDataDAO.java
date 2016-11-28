package jums;

import base.DBManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 * ユーザー情報を格納するテーブルに対しての操作処理を包括する
 * DB接続系はDBManagerクラスに一任
 * 基本的にはやりたい1種類の動作に対して1メソッド
 * @author hayashi-s
 */
public class UserDataDAO {
    
    //インスタンスオブジェクトを返却させてコードの簡略化
    public static UserDataDAO getInstance(){
        return new UserDataDAO();
    }
    
    /**
     * データの挿入処理を行う。現在時刻は挿入直前に生成
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     */
    public void insert(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("INSERT INTO user_t(name,birthday,tell,type,comment,newDate) VALUES(?,?,?,?,?,?)");
            st.setString(1, ud.getName());
            st.setDate(2, new java.sql.Date(ud.getBirthday().getTime()));//指定のタイムスタンプ値からSQL格納用のDATE型に変更
            st.setString(3, ud.getTell());
            st.setInt(4, ud.getType());
            st.setString(5, ud.getComment());
            st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            System.out.println("insert completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }
    
    /**
     * 　データの検索処理を行う。
     * 　検索結果の各行ごとにUserDataDTOインスタンスに格納し、
     * ArrayListに収める作業を繰り返す。
     */
    public ArrayList<UserDataDTO> search(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            String sql = "SELECT * FROM user_t";
            
            //入力されているか否かの情報を、flagで管理
            boolean flag1 ,flag2 ,flag3;
            flag1 = flag2 = flag3 = false;
            
            if(!ud.getName().equals("")) flag1 = true;
            if(ud.getBirthday()!=null) flag2 = true;
            if(ud.getType()!=0) flag3 = true;           
            
            //flagで場合分けしてSQL文に文章を追加
            if(flag1){
                sql += " WHERE name like ?";
            }
            if (flag2) {
                if(!flag1){
                    sql += " WHERE birthday like ?";
                }else{
                    sql += " AND birthday like ?";
                }
            }
            if (flag3) {
                if(!flag1 && !flag2){
                    sql += " WHERE type like ?";
                }else{
                    sql += " AND type like ?";
                }
            }
            
            st =  con.prepareStatement(sql);
            
            //flagで場合分けして、SQL文に代入する値を決定
            if (flag1) {
                st.setString(1, "%"+ud.getName()+"%");
            }
            if (flag2) {
                if(!flag1){
                    st.setString(1, "%"+ new SimpleDateFormat("yyyy").format(ud.getBirthday())+"%");
                }else{
                    st.setString(2, "%"+ new SimpleDateFormat("yyyy").format(ud.getBirthday())+"%");
                }
            }
            if(flag3){
                if(!flag1 && !flag2){
                    st.setInt(1, ud.getType());
                }else if(!flag1 || !flag2){
                    st.setInt(2, ud.getType());
                }else{
                    st.setInt(3, ud.getType());
                }
            }
            
            ResultSet rs = st.executeQuery();
            
            /*
            * 　検索結果の各行ごとにUserDataDTO resultUd_inに格納し、
            * ArrayList<UserDataDTO> resultUdに収める作業を繰り返す。
            */
            ArrayList<UserDataDTO> resultUd = new ArrayList<UserDataDTO>();
            while(rs.next()){
                UserDataDTO resultUd_in = new UserDataDTO();
                resultUd_in.setUserID(rs.getInt(1));
                resultUd_in.setName(rs.getString(2));
                resultUd_in.setBirthday(rs.getDate(3));
                resultUd_in.setTell(rs.getString(4));
                resultUd_in.setType(rs.getInt(5));
                resultUd_in.setComment(rs.getString(6));
                resultUd_in.setNewDate(rs.getTimestamp(7));
                
                resultUd.add(resultUd_in);
            }
            System.out.println("search completed");

            return resultUd;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }
    
    /**
     * ユーザーIDによる1件のデータの検索処理を行う。
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 検索結果
     */
    public UserDataDTO searchByID(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            String sql = "SELECT * FROM user_t WHERE userID = ?";
            
            st =  con.prepareStatement(sql);
            st.setInt(1, ud.getUserID());
            
            ResultSet rs = st.executeQuery();
            rs.next();
            UserDataDTO resultUd = new UserDataDTO();
            resultUd.setUserID(rs.getInt(1));
            resultUd.setName(rs.getString(2));
            resultUd.setBirthday(rs.getDate(3));
            resultUd.setTell(rs.getString(4));
            resultUd.setType(rs.getInt(5));
            resultUd.setComment(rs.getString(6));
            resultUd.setNewDate(rs.getTimestamp(7));
            
            System.out.println("searchByID completed");

            return resultUd;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }
    
    /* 1件のデータの削除処理を行う。 */
    public void delete(UserDataDTO ud) throws SQLException{    
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            String sql = "DELETE FROM user_t WHERE userID = ?";
            
            st =  con.prepareStatement(sql);
            st.setInt(1, ud.getUserID());
            
            st.executeUpdate();
            
            System.out.println("searchByID completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    /* テーブルの更新を行う */
    public void update(ArrayList<String> uList,UserDataDTO uData) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            String sql = "UPDATE user_t SET ";
            
            //ArrayListに値が格納されているかの情報を、flagで管理
            boolean flag1 ,flag2 ,flag3 ,flag4 ,flag5;
            flag1 = flag2 = flag3 = flag4 = flag5 = false;
            
            if(uList.contains("name")) flag1 = true;
            if(uList.contains("birthday")) flag2 = true;
            if(uList.contains("tell")) flag3 = true;
            if(uList.contains("type")) flag4 = true;
            if(uList.contains("comment")) flag5 = true;
            
            //flagで場合分けして、SQL文に代入する値を決定
            if(flag1){
                sql += "name = ?";
            }
            if(flag2){
                if(!flag1){
                    sql += "birthday = ?";
                }else{
                    sql += ",birthday = ?";
                }
            }
            if(flag3){
                if(!flag1 && !flag2){
                    sql += "tell = ?";
                }else{
                    sql += ",tell = ?";
                }
            }
            if(flag4){
                if(!flag1 && !flag2 && !flag3){
                    sql += "type = ?";
                }else{
                    sql += ",type = ?";
                }
            }
            if(flag5){
                if(!flag1 && !flag2 && !flag3 && !flag4){
                    sql += "comment = ?";
                }else{
                    sql += ",comment = ?";
                }
            }
            
            sql += " WHERE userID = ?";
            
            //flagで場合分けして、SQL文に代入する
            st =  con.prepareStatement(sql);
            if(flag1)
                st.setString(1, uData.getName());           
            if(flag2)
                //指定のタイムスタンプ値からSQL格納用のDATE型に変更
                st.setDate(uList.indexOf("birthday") + 1, new java.sql.Date(uData.getBirthday().getTime()));
            if(flag3)
                st.setString(uList.indexOf("tell") + 1, uData.getTell());
            if(flag4)
                st.setInt(uList.indexOf("type") + 1, uData.getType());
            if(flag5)
                st.setString(uList.indexOf("comment") + 1, uData.getComment());
            st.setInt(uList.size() + 1, uData.getUserID());
            st.executeUpdate();
            
            System.out.println("searchByID completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
}
