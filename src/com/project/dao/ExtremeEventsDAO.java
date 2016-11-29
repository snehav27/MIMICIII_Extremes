package com.project.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExtremeEventsDAO {

	public ArrayList<ArrayList<String>> getDescendingExtremes(HashMap<String, String> parameters, String order) throws SQLException{
		StringBuilder query = new StringBuilder();
		
		StringBuilder whereClause = new StringBuilder();
		for(Map.Entry<String, String> param: parameters.entrySet()) {
			whereClause.append("and a."+param.getKey()+ " = ?" );
		}
		
		query.append("Select tb.valueom, tb.valuenum, tb.label  from (");
		query.append("select  ROW_NUMBER()OVER (PARTITION BY row.label order by row.valuenum " + order + ") AS r, row.valueom, row.valuenum, row.label from ");
		query.append("(select   distinct c.valueom, c.valuenum, d.label from admissions a, chartEvents c,(select itemid, label from d_items  group by label, itemid)d where ");
		query.append("c.valuenum <> 0 and c.hadm_id = a.hadm_id and d.itemid = c.itemid ");
		query.append(whereClause);
		query.append(" order by c.valuenum ) row )  tb where tb.r<=10 order by tb.label, tb.valuenum "+order);
		
		System.out.println(query.toString());
		PreparedStatement getExtremesStmt = Demographics.conn.prepareStatement(query.toString());
		int index = 1;
		for(Map.Entry<String, String> param: parameters.entrySet()) {
			getExtremesStmt.setString(index++, param.getValue());
		}
		ResultSet rs = getExtremesStmt.executeQuery();
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String>  header = new ArrayList<String>();
		header.add("Units");
		header.add("Value");
		header.add("Description");
		result.add(header);
		while(rs.next()) {
			ArrayList<String>  res = new ArrayList<String>();
			res.add(rs.getString(1));
			res.add(rs.getString(2));
			res.add(rs.getString(3));
			result.add(res);
		}
		return result;
	}
}
