package com.assignment.libraryJdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.assignment.libraryJdbc.entities.Subject;
import com.assignment.libraryJdbc.util.ConnectionFactory;

public class SubjectDAO implements ISubjectDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.assignment.libraryJdbc.dao.ISubjectDAO#searchSubject(java.lang.String)
	 */
	public List<Subject> searchSubject(String subjectName) {
		Connection connection = ConnectionFactory.getConnection();
		List<Subject> subjects = new ArrayList<Subject>();
		try {
			String query = "select subject_id, subtitle, duration from subject where subtitle like ?";
			PreparedStatement ps = connection
					.prepareStatement(query);
			ps.setString(1, "%" + subjectName + "%");
			getSubjectResults(subjects, ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subjects;
	}

	/**
	 * @param subjects
	 * @param ps
	 * @throws SQLException
	 */
	private void getSubjectResults(List<Subject> subjects, PreparedStatement ps) throws SQLException {
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Subject subject = new Subject();
			subject.setSubjectId(rs.getLong("subject_id"));
			subject.setSubtitle(rs.getString("subtitle"));
			subject.setDurationInHours(rs.getInt("duration"));
			subjects.add(subject);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.assignment.libraryJdbc.dao.ISubjectDAO#addSubject(com.assignment.
	 * libraryJdbc.entities.Subject)
	 */
	public boolean addSubject(Subject subject) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO subject " + "(subtitle, duration) " + "VALUES (?, ?)");
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.assignment.libraryJdbc.dao.ISubjectDAO#deleteSubject(long)
	 */
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

	/* (non-Javadoc)
	 * @see com.assignment.libraryJdbc.dao.ISubjectDAO#getSubjects()
	 */
	@Override
	public List<Subject> getSubjects() {
		Connection connection = ConnectionFactory.getConnection();
		List<Subject> subjects = new ArrayList<Subject>();
		try {
			String query = "select subject_id, subtitle, duration from subject";
			PreparedStatement ps = connection
					.prepareStatement(query);
			getSubjectResults(subjects, ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subjects;
	}

}
