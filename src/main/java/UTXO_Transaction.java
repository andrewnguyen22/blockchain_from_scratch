import java.security.PublicKey;
import java.util.ArrayList;

public class UTXO_Transaction {
    public String transactionID;
    public PublicKey sender;
    public PublicKey receiver;
    public float value;
    public byte[] signature;
    public ArrayList<TransactionInput> inputs = new ArrayList<>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<>();

    private static int sequence = 0; // a rough count of how many transactions have been generated.

    public UTXO_Transaction (PublicKey from, PublicKey to, float val, ArrayList<TransactionInput> input){
        this.sender = from;
        this.receiver= to;
        this.value = val;
        this.inputs = input;
    }

    // This Calculates the transaction hash (which will be used as its Id)
    private String calulateHash() {
        sequence++; //increase the sequence to avoid 2 identical transactions having the same hash
        return Utility.SHA256(
                Utility.getStringFromKey(sender) +
                        Utility.getStringFromKey(receiver) +
                        Float.toString(value) + sequence
        );
    }
}
