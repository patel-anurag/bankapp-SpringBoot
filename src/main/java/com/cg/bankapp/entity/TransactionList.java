//package com.cg.bankapp.entity;
//
//
//import java.util.List;
//
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlRootElement;
//
//@XmlRootElement
//public class TransactionList {
//
//	@XmlElement(name = "transaction")
//	private List<Transaction> transaction;
//
//	public TransactionList() {
//		
//	}
//	public TransactionList(List<Transaction> transaction) {
//		super();
//		this.transaction = transaction;
//	}
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((transaction == null) ? 0 : transaction.hashCode());
//		return result;
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		TransactionList other = (TransactionList) obj;
//		if (transaction == null) {
//			if (other.transaction != null)
//				return false;
//		} else if (!transaction.equals(other.transaction))
//			return false;
//		return true;
//	}
//
//
//}
