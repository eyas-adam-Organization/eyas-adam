public class ProgramData {
    public String programTitle;
    public int value;
    public ProgramData(String programTitle,int value){
        this.programTitle=programTitle;
        this.value=value;
    }
    @Override
    public boolean equals(Object programData1){
        ProgramData programDatatemp=(ProgramData) programData1;
        if(this.programTitle.equals(programDatatemp.programTitle)&&this.value==programDatatemp.value)return true;
        return false;

    }
}
