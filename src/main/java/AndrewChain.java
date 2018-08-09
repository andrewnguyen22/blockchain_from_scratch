import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class AndrewChain {
    public static ArrayList<Block> blockchain = new ArrayList<>();       // Arraylist with all of the blocks
    public static int difficulty = 5;                                    // How hard it is to solve the POW problem
    public static int blockNumber = 1;                                   // Number of the current block

    public static void main(String[] args) {
        Block genesisBlock = new Block("I am the genesis block", "0");
        blockchain.add(genesisBlock);

        for (int i = 0; i < 20; ++i) {
            createNewBlock();
        }

        isChainValid();
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);
    }

    public static void createNewBlock() {
        blockchain.add(new Block("I am block # " + blockNumber, blockchain.get(blockNumber - 1).hash));
        System.out.println("Mining Block " + blockNumber + " ...");
        blockchain.get(blockNumber).mineBlock(difficulty);
        System.out.println("Block Successfully Mined!");
        ++blockNumber;
    }

    public static boolean isChainValid() {
        Block currentBlock, previousBlock;

        //loop through blockchain to check hashes:
        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            //compare registered hash and calculated hash:
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }
}
