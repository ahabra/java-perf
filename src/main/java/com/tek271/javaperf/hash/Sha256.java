package com.tek271.javaperf.hash;

import com.google.common.hash.Hashing;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Sha256 {
	private static final String TEXT = "Hello World 1234";

	public Sha256() {
		// used for sha3_256_keccak
		Security.addProvider(new BouncyCastleProvider());
	}

	public String sha256_jdk() {
		MessageDigest digest = getMessageDigestFor("SHA-256");
		byte[] bytes = digest.digest(TEXT.getBytes(UTF_8));
		return bytesToHex(bytes);
	}

	private MessageDigest getMessageDigestFor(String algorithm) {
		try {
			return MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	private String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b: bytes) {
			sb.append(Character.forDigit((b >> 4) & 0xF, 16));
			sb.append(Character.forDigit(b & 0xF, 16));
		}
		return sb.toString();
	}

	public String sha256_guava() {
		return Hashing.sha256().hashString(TEXT, UTF_8).toString();
	}

	public String sha256_apacheCodec() {
		return DigestUtils.sha256Hex(TEXT);
	}

	public String sha3_256_jdk() {
		MessageDigest digest = getMessageDigestFor("SHA3-256");
		byte[] bytes = digest.digest(TEXT.getBytes(UTF_8));
		return bytesToHex(bytes);
	}

	public String sha3_256_apacheCodec() {
		return new DigestUtils("SHA3-256").digestAsHex(TEXT);
	}

	public String sha3_256_keccak() {
		MessageDigest digest = getMessageDigestFor("Keccak-256");
		byte[] bytes = digest.digest(TEXT.getBytes(UTF_8));
//		return bytesToHex(bytes);
		return new String(Hex.encode(bytes));
	}


}
