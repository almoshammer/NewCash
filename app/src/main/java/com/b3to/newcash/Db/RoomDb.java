package com.b3to.newcash.Db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.b3to.newcash.Models.Account;
import com.b3to.newcash.Models.AccountType;
import com.b3to.newcash.Models.Currency;
import com.b3to.newcash.Models.Transaction;

import java.util.concurrent.Executors;

@Database(version = 10, entities = {Account.class, AccountType.class, Currency.class, Transaction.class}, exportSchema = false)
public abstract class RoomDb extends androidx.room.RoomDatabase {
    private static Context mContext;
    private static RoomDb instance;
    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            if (mContext != null) {
                //  MyToolsCls.setUserToken(mContext);
            }

            // FirebaseMessaging.getInstance().subscribeToTopic("STUDENT_HELPER_NOTIFICATIONS");


            Executors.newSingleThreadExecutor().execute(()->{

                instance.GetDaoAccountType().insert(new AccountType("Fund"));
                instance.GetDaoAccountType().insert(new AccountType("Expenses"));

                instance.GetDaoAccount().insert(new Account(2,"Urielle Mcguire","1-372-337-8754","(598) 719-4532","(598) 719-4532","nec ante blandit viverra. Donec tempus, lorem fringilla ornare placerat",null));
                instance.GetDaoAccount().insert(new Account(1,"Bianca Jefferson","1-612-493-0632","(219) 394-5472","(435) 301-3630","vel est tempor bibendum. Donec",null));
                instance.GetDaoAccount().insert(new Account(1,"Velma Kerr","(641) 871-5574","1-817-180-4635","1-933-727-3734","velit egestas lacinia. Sed congue, elit",null));
                instance.GetDaoAccount().insert(new Account(2,"Bree Compton","1-279-315-4829","1-962-830-5146","1-826-729-9666","tellus id nunc interdum feugiat. Sed nec metus facilisis lorem",null));
                instance.GetDaoAccount().insert(new Account(2,"Keane Nielsen","(182) 581-9877","1-310-758-9405","1-282-713-735","magna et ipsum cursus vestibulum.",null));
                instance.GetDaoAccount().insert(new Account(1,"George Gillespie","(446) 454-5666","(367) 379-1991","1-574-193-5744","at lacus. Quisque purus",null));
                instance.GetDaoAccount().insert(new Account(2,"Dean Marshall","1-702-568-1159","(176) 282-8217","(266) 253-2082","quam, elementum at, egestas a, scelerisque sed, sapien. Nunc",null));
                instance.GetDaoAccount().insert(new Account(1,"Shaeleigh Atkins","(641) 355-5165","1-854-907-9049","1-448-413-9282","aliquam adipiscing lacus. Ut nec urna et arcu imperdiet ullamcorper",null));
                instance.GetDaoAccount().insert(new Account(1,"Chaney Hale","1-368-662-6633","(646) 666-7151","1-341-858-8852","Mauris vestibulum, neque sed dictum",null));
                instance.GetDaoAccount().insert(new Account(1,"Liberty Shepherd","1-773-876-7725","(733) 276-3050","(766) 879-4138","eget laoreet posuere, enim nisl elementum purus",null));
                instance.GetDaoAccount().insert(new Account(1,"Keane Pena","(791) 146-6283","1-361-601-8166","1-837-255-5670","pharetra, felis eget varius ultrices, mauris ipsum porta elit",null));
                instance.GetDaoAccount().insert(new Account(2,"Gary Heath","1-134-734-2784","(565) 396-0984","(300) 886-7827","Morbi neque tellus, imperdiet non",null));

                instance.GetDaoCurrency().insert(new Currency(1,"US Dollar","USD"));

                instance.GetDaoTransaction().insert(new Transaction(12,85.38,1,"Quisque purus sapien, gravida non, sollicitudin a, malesuada", 1643581341082L,0,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(10,23.48,1,"Duis elementum, dui quis accumsan convallis, ante lectus",1651322230221L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(10,64.07,1,"Cras vehicula aliquet libero. Integer in magna. Phasellus dolor elit",1633413126799L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(8,13.44,1,"vel sapien imperdiet ornare. In faucibus. Morbi vehicula. Pellentesque tincidunt",1657022739537L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(4,9.38,1,"vestibulum lorem, sit amet ultricies sem magna nec",1661391852953L,0,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(12,24.82,1,"Aenean egestas hendrerit neque. In ornare",1624898198667L,0,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(10,9.02,1,"penatibus et magnis dis parturient montes, nascetur ridiculus mus. Proin",1665535061158L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(3,48.41,1,"et nunc. Quisque ornare tortor at",1662622690384L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(9,63.16,1,"Sed neque. Sed eget lacus. Mauris non",1682426203614L,0,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(12,24.64,1,"consequat purus. Maecenas libero est, congue",1637770708093L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(4,28.02,1,"metus facilisis lorem tristique aliquet. Phasellus fermentum",1620823407679L,0,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(7,98.25,1,"lectus rutrum urna, nec luctus",1629832387578L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(2,78.02,1,"sed, est. Nunc laoreet lectus quis massa.",1681047969144L,0,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(2,35.06,1,"felis. Nulla tempor augue ac",1628639647301L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(4,57.24,1,"nulla. Integer urna. Vivamus molestie dapibus ligula. Aliquam erat volutpat. Nulla",1623968182157L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(9,69.75,1,"arcu. Nunc mauris. Morbi non sapien molestie",1621022515432L,0,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(8,98.82,1,"lobortis risus. In mi pede, nonummy ut, molestie",1646486185710L,0,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(5,83.09,1,"eros turpis non enim. Mauris",1688448685343L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(9,29.78,1,"arcu. Sed et libero. Proin mi. Aliquam gravida",1622041155268L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(4,36.93,1,"venenatis a, magna. Lorem ipsum dolor sit",1692341938279L,0,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(2,93.98,1,"augue scelerisque mollis. Phasellus libero",1624278773792L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(5,16.84,1,"id, ante. Nunc mauris sapien, cursus in, hendrerit",1621017632287L,0,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(6,35.94,1,"id sapien. Cras dolor dolor,",1674768763996L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(9,96.57,1,"convallis ligula. Donec luctus aliquet",1644155565259L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(7,69.82,1,"in, tempus eu, ligula. Aenean euismod mauris eu elit. Nulla facilisi.",1652603441599L,0,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(8,31.98,1,"Ut tincidunt orci quis lectus. Nullam suscipit, est ac facilisis",1671653075391L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(11,10.29,1,"nunc interdum feugiat. Sed nec",1623149245253L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(8,9.44,1,"tincidunt nibh. Phasellus nulla. Integer vulputate, risus",1670126121230L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(3,69.36,1,"Cras dolor dolor",160909809,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(3,95.92,1,"eget massa. Suspendisse eleifend. Cras sed",1698205948906L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(7,80.15,1,"sem elit, pharetra ut, pharetra",1659941885518L,0,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(2,82.36,1,"quis massa. Mauris vestibulum, neque",1658479847030L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(7,42.70,1,"vehicula risus. Nulla eget metus eu erat semper rutrum. Fusce",1645969517270L,0,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(8,15.53,1,"adipiscing ligula. Aenean gravida nunc sed pede. Cum sociis",1650063008840L,0,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(7,67.40,1,"Quisque tincidunt pede ac urna. Ut tincidunt vehicula risus.",1624400005317L,0,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(5,78,1,"gravida. Aliquam tincidunt, nunc ac mattis ornare, lectus ante",1680218867687L,0,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(5,78.88,1,"est tempor bibendum. Donec felis orci, adipiscing non,",1646181278594L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(12,45.64,1,"Sed congue, elit sed consequat auctor, nunc nulla vulputate",1628403345748L,0,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(11,30.12,1,"Duis mi enim, condimentum eget, volutpat ornare, facilisis",1678429804414L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(2,47.06,1,"lacinia orci, consectetuer euismod est arcu ac orci.",1622281274325L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(4,14.31,1,"sed dictum eleifend, nunc risus varius orci, in consequat enim",1667559323556L,0,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(11,53,1,"cubilia Curae Donec tincidunt. Donec vitae",1668953354549L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(8,100,1,"amet orci. Ut sagittis lobortis mauris.",1691904410065L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(8,12,1,"eu arcu. Morbi sit amet massa. Quisque porttitor eros",1680340203553L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(8,74,1,"parturient montes, nascetur ridiculus mus. Aenean eget magna. Suspendisse",1640472284114L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(11,59,1,"quis, tristique ac, eleifend vitae, erat. Vivamus nisi. Mauris nulla.",1680845390083L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(2,87,1,"blandit. Nam nulla magna, malesuada vel, convallis in, cursus et, eros.",1689813666743L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(10,77,1,"ligula tortor, dictum eu, placerat eget, venenatis",1644418641036L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(3,60,1,"Quisque purus sapien, gravida non, sollicitudin a, malesuada",1643271205090L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(3,65,1,"Duis elementum, dui quis accumsan convallis, ante lectus",1668213426031L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(4,12,1,"Cras vehicula aliquet libero. Integer in magna. Phasellus dolor elit",1641065572969L,0,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(3,9,1,"vel sapien imperdiet ornare. In faucibus. Morbi vehicula. Pellentesque tincidunt",1650171078568L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(2,46,1,"vestibulum lorem, sit amet ultricies sem magna nec",1661271396691L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(6,62,1,"Aenean egestas hendrerit neque. In ornare",1660384864954L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(11,21,1,"penatibus et magnis dis parturient montes, nascetur ridiculus mus. Proin",1637377759695L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(7,86,1,"et nunc. Quisque ornare tortor at",1646650487601L,0,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(8,86.65,1,"consequat purus. Maecenas libero est, congue",1649041473542L,1,"faucibus lectus, a sollicitudin orci sem eget"));
                instance.GetDaoTransaction().insert(new Transaction(11,9.94,1,"metus facilisis lorem tristique aliquet. Phasellus fermentum convallis",1629830073515L,1,"non, cursus non, egestas a, dui. Cras pellentesque. Sed"));
                instance.GetDaoTransaction().insert(new Transaction(7,79.65,1,"lectus rutrum urna, nec luctus",1666793221306L,0,"est tempor bibendum. Donec felis orci, adipiscing non,"));
            });
        }

        @Override
        public void onOpen(SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    // Singleton Method to get instance
    public static synchronized RoomDb getInstance(Context context) {
        mContext = context;
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), RoomDb.class, "db_new_cash") // database name
                    .addCallback(roomCallback)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract com.b3to.newcash.Daos.AccountType GetDaoAccountType();

    public abstract com.b3to.newcash.Daos.Account GetDaoAccount();

    public abstract com.b3to.newcash.Daos.Currency GetDaoCurrency();

    public abstract com.b3to.newcash.Daos.Transaction GetDaoTransaction();

}
