package com.assignment.libraryJdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.assignment.libraryJdbc.entities.Subject;
import com.assignment.libraryJdbc.util.ConnectionFactory;

public class SubjectDAO implements ISubjectDAO {

	public List<Subject> searchSubject(String subjectName) {
		return null;
	}

	public boolean addSubject(Subject subject) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO subject "
					+ "(subtitle, duration) " + "VALUES (?, ?)");
			ps.setString(1, subject.getSubtitle());
			ps.setInt(2, subject.getDurationInHours());
			
			int i = ps.executeUpdate();
			if (i == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean deleteSubject(long subjectId) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("delete from subject where subject_id = ?");
			ps.setLong(1, subjectId);
			int i = ps.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
