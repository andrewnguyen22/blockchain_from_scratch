import javax.rmi.CORBA.Util;
import java.util.Date;

/*
Contains all of the data within the block
 */
public class Block {
    public String hash;                 // Hash of current block
    public String previousHash;         // Hash of previous block
    public String data;                 // Data within the block
    private long timeStamp;             // Time of the block
    private int nonce;

    /**
     * Block Constructor: Creates a new block by passing in data and string from previous hash
     *
     * @param data         of the block
     * @param previousHash hash of the block before it
     */
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    /**
     * Calculates the hash of the block
     * NOTE: notice how the hash is calculated out of the previous hash, the timestamp, and the data
     *
     * @return Hash of the current block
     */
    public String calculateHash() {
        return Utility.SHA256(previousHash + Integer.toString(nonce) + Long.toString(timeStamp) + data);
    }

    /**
     * Proof Of Work Algo
     * @param difficulty
     */
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while(!hash.substring(0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

}
