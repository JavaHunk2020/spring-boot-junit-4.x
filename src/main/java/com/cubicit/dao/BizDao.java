package com.cubicit.dao;

import java.io.IOException;
import java.sql.Types;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.cubicit.controller.Biz;
import com.cubicit.controller.BizChild;

@Repository
public class BizDao {
	
	@Autowired // ByType, @Qualifier ,@ByName
	private JdbcTemplate jdbcTemplate;
	
	public Biz findById(int did){
		String sql="select id,name,brand,doe from biz_service_tbl where id = "+did;
		List<Biz> bizList=jdbcTemplate.query(sql, new BeanPropertyRowMapper(Biz.class));
		/*if(bizList.size()==1){
			return bizList.get(0);
		}
		return null;*/
		return bizList.size()==1?bizList.get(0):null;
	}
	
	//true, false
	public boolean isAuth(String name,String password){
		List<Biz> bizList=jdbcTemplate.query("select id,name,brand,doe from biz_service_tbl where name =? and brand=?",
				new Object[]{name,password},
				new BeanPropertyRowMapper(Biz.class));
		return bizList.size() > 0;
	}
	
	public String updateByName(String name,String brand){
		int rows=jdbcTemplate.update("update biz_service_tbl set brand=? where name = ?",brand,name);
		String result="Number of row update is  = "+rows;
		return result;
	}
	
	public String deleteByName(String name){
		int rows=jdbcTemplate.update("delete from biz_service_tbl where name = ?",name);
		String result="Number of row deleted is  = "+rows;
		return result;
	}
	
	
	
	
	public String deletecPhoto(int id){
		int rows=jdbcTemplate.update("delete from biz_photos_tbl where bizid = ?",id);
		String result="Number of row deleted is  = "+rows;
		return result;
	}
	
	
	public String deleteById(int id){
		int rows=jdbcTemplate.update("delete from biz_service_tbl where id = ?",id);
		String result="Number of row deleted is  = "+rows;
		return result;
	}
	
	
	public void update(Biz biz){
		String sql="update biz_service_tbl set name=?,brand=? where id=?";
		Object[] data={biz.getName(),biz.getBrand(),biz.getId()};
		jdbcTemplate.update(sql,data);
	}
	
	
	
	public byte[] findPhotoById(int dbid){
		byte[] photo=jdbcTemplate.queryForObject("select photo from biz_service_tbl where id = "+dbid, byte[].class);
		return photo;
	}
	
	public byte[] cfindPhotoById(int dbid){
		byte[] photo=jdbcTemplate.queryForObject("select photo from biz_photos_tbl where bizid = "+dbid, byte[].class);
		return photo;
	}
	
	public void save(Biz biz){
		try {
			MultipartFile[] multipartFiles=biz.getFile();
			int pid=0;
			if(multipartFiles.length>0){
				byte[] photo=multipartFiles[0].getBytes();
				LobHandler lobHandler=new DefaultLobHandler();
				SqlLobValue sqlLobValue=new SqlLobValue(photo,lobHandler);
				String sql="insert into biz_service_tbl(name,brand,doe,photo) values(?,?,?,?)";
				Object[] data={biz.getName(),biz.getBrand(),biz.getDoe(),sqlLobValue};
				//This is just syntax !!!!!!!!!!!!!!!!!!!
				int dataType[] ={Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.BLOB};
				jdbcTemplate.update(sql,data,dataType);	
				String maxid="select max(id) from biz_service_tbl";
				pid=jdbcTemplate.queryForObject(maxid, Integer.class);
			}
			int i=0;
			for(MultipartFile multipartFile:multipartFiles){
				if(i>0){
					byte[] photo=multipartFile.getBytes();
					LobHandler lobHandler=new DefaultLobHandler();
					SqlLobValue sqlLobValue=new SqlLobValue(photo,lobHandler);
					String imagesql="insert into biz_photos_tbl(photo,pid) values(?,?)";
					Object[] data={sqlLobValue,pid};
					//This is just syntax !!!!!!!!!!!!!!!!!!!
					int dataType[] ={Types.BLOB,Types.INTEGER};
					jdbcTemplate.update(imagesql,data,dataType);	
				}
				 i++;	
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Biz> findAll(){
		List<Biz> bizList=jdbcTemplate.query("select id,name,brand,doe from biz_service_tbl", new BeanPropertyRowMapper(Biz.class));
		for(Biz biz:bizList){
			String sql="select bizid from biz_photos_tbl where pid = "+biz.getId()+" order by porder";
			List<Integer> cimageIds=jdbcTemplate.queryForList(sql,Integer.class);
			biz.setCimageIds(cimageIds);
		}
		return bizList;
	}

	public void rotateImageRight(int dbid) {
		String sql="select bizid,porder from biz_photos_tbl where pid = "+dbid+" order by porder";
		List<BizChild> bizChilds=jdbcTemplate.query(sql,new BeanPropertyRowMapper(BizChild.class));
		int size=bizChilds.size();
		BizChild fbizChild=bizChilds.get(0);
		fbizChild.setPorder(2);
		BizChild lbizChild=bizChilds.get(size-1);
		lbizChild.setPorder(1);
		int index=0;
		for(BizChild bizChild:bizChilds){
			index++;
			if(index==1 || index==size){
				continue;
			}
			bizChild.setPorder(index+1);
		}
		
		for(BizChild bizChild:bizChilds){
			String usql="update biz_photos_tbl set porder="+bizChild.getPorder()+" where  bizid = "+bizChild.getBizid();
			jdbcTemplate.update(usql);
		}
		
		//Map<Integer,Integer> maps=bizChilds.stream().collect(Collectors.toMap((BizChild s)->s.getBizid(), (BizChild t)->t.getPorder()));
		/*Map<Integer,Integer> maps=new LinkedHashMap<Integer, Integer>();
		for(BizChild bizChild:bizChilds){
			maps.put(bizChild.getBizid(), bizChild.getPorder());
		}*/
		
		
	}

}
