package com.java.utils;

import com.mysql.jdbc.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class IpProcesser {

	private String Country, LocalStr;
	private long IPN;
	private int RecordCount, CountryFlag;
	private long RangE, RangB, OffSet, StartIP, EndIP, FirstStartIP,
			LastStartIP, EndIPOff;
	private RandomAccessFile fis;
	private byte[] buff;
	static String DbPath = "D:\\work\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp5\\wtpwebapps\\qegoo\\WEB-INF\\classes\\QQWry.Dat";
	
	static String DbPath2 = "E:\\QQWry.Dat";

	public static void main(String[] args) throws Exception {

		System.out.println(DbPath);
		System.out.println(getIPAddress("127.0.0.1", DbPath));

		// ipProcesser.seek("192.123.12.123");
		// System.out.println(ipProcesser.getCountry() + " " +
		// ipProcesser.getLocal());
	}

	public static String getIPAddress(String ip, String dbpath) {
		String address = "";
		if (StringUtils.isNullOrEmpty(ip) || StringUtils.isNullOrEmpty(dbpath)) {
			return address;
		}
		IpProcesser ipProcesser = new IpProcesser();
		try {
			ipProcesser.seek(ip, dbpath);
			String country = ipProcesser.getCountry();
			country = new String(country.getBytes(),"utf-8");
			String local = ipProcesser.getLocal();
			local = new String(local.getBytes(),"utf-8");
			address = country + " " + local;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;
	}

	
	
	private long B2L(byte[] b) {
		long ret = 0;
		for (int i = 0; i < b.length; i++) {
			long t = 1L;
			for (int j = 0; j < i; j++)
				t = t * 256L;
			ret += ((b[i] < 0) ? 256 + b[i] : b[i]) * t;
		}
		return ret;
	}

	private long ipToInt(String ip) {
		String[] arr = ip.split("\\.");
		long ret = 0;
		for (int i = 0; i < arr.length; i++) {
			long l = 1;
			for (int j = 0; j < i; j++)
				l *= 256;
			try {
				ret += Long.parseLong(arr[arr.length - i - 1]) * l;
			} catch (Exception e) {
				ret += 0;
			}
		}
		return ret;
	}

	public void seek(String ip, String filePath) throws Exception {
		this.IPN = ipToInt(ip);
		fis = new RandomAccessFile(filePath, "r");
		buff = new byte[4];
		fis.seek(0);
		fis.read(buff);
		FirstStartIP = this.B2L(buff);
		fis.read(buff);
		LastStartIP = this.B2L(buff);
		RecordCount = (int) ((LastStartIP - FirstStartIP) / 7);
		if (RecordCount <= 1) {
			LocalStr = Country = "未知";
			throw new Exception();
		}

		RangB = 0;
		RangE = RecordCount;
		long RecNo;

		do {
			RecNo = (RangB + RangE) / 2;
			getStartIP(RecNo);
			if (IPN == StartIP) {
				RangB = RecNo;
				break;
			}
			if (IPN > StartIP)
				RangB = RecNo;
			else
				RangE = RecNo;
		} while (RangB < RangE - 1);

		getStartIP(RangB);
		getEndIP();
		getCountry(IPN);

		fis.close();
	}

	private String getFlagStr(long OffSet) throws IOException {
		int flag = 0;
		do {
			fis.seek(OffSet);
			buff = new byte[1];
			fis.read(buff);
			flag = (buff[0] < 0) ? 256 + buff[0] : buff[0];
			if (flag == 1 || flag == 2) {
				buff = new byte[3];
				fis.read(buff);
				if (flag == 2) {
					CountryFlag = 2;
					EndIPOff = OffSet - 4;
				}
				OffSet = this.B2L(buff);
			} else
				break;
		} while (true);

		if (OffSet < 12) {
			return "";
		} else {
			fis.seek(OffSet);
			return getStr();
		}
	}

	private String getStr() throws IOException {
		long l = fis.length();
		ByteArrayOutputStream byteout = new ByteArrayOutputStream();
		byte c = fis.readByte();
		do {
			byteout.write(c);
			c = fis.readByte();
		} while (c != 0 && fis.getFilePointer() < l);
		return byteout.toString();
	}

	private void getCountry(long ip) throws IOException {
		if (CountryFlag == 1 || CountryFlag == 2) {
			Country = getFlagStr(EndIPOff + 4);
			if (CountryFlag == 1) {
				LocalStr = getFlagStr(fis.getFilePointer());
				if (IPN >= ipToInt("255.255.255.0")
						&& IPN <= ipToInt("255.255.255.255")) {
					LocalStr = getFlagStr(EndIPOff + 21);
					Country = getFlagStr(EndIPOff + 12);
				}
			} else {
				LocalStr = getFlagStr(EndIPOff + 8);
			}
		} else {
			Country = getFlagStr(EndIPOff + 4);
			LocalStr = getFlagStr(fis.getFilePointer());
		}
	}

	private long getEndIP() throws IOException {
		fis.seek(EndIPOff);
		buff = new byte[4];
		fis.read(buff);
		EndIP = this.B2L(buff);
		buff = new byte[1];
		fis.read(buff);
		CountryFlag = (buff[0] < 0) ? 256 + buff[0] : buff[0];
		return EndIP;
	}

	private long getStartIP(long RecNo) throws IOException {
		OffSet = FirstStartIP + RecNo * 7;
		fis.seek(OffSet);
		buff = new byte[4];
		fis.read(buff);
		StartIP = this.B2L(buff);
		buff = new byte[3];
		fis.read(buff);
		EndIPOff = this.B2L(buff);
		return StartIP;
	}

	public String getLocal() {
		return this.LocalStr;
	}

	public String getCountry() {
		return this.Country;
	}

	public void setPath(String path) {
		this.DbPath = path;
	}

}
