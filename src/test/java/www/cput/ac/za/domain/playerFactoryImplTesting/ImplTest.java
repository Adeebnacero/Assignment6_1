package www.cput.ac.za.domain.playerFactoryImplTesting;


import org.testng.Assert;
import org.testng.annotations.Test;
import www.cput.ac.za.domain.player.Player;
import www.cput.ac.za.repository.player.PlayerRepository;

/**
 * Created by Adeebo on 2016/04/24.
 */
public class ImplTest {

    @Test
    public void playerRepositoryImplCreate()
    {
        // Arrange
        Player player = new Player();
        Player.firstName = COLUMN_FIRSTNAME;

        // Act
        playerRepositoryImpl.Add(player);

        // Assert
        Assert.assertNotNull(playerRepositoryImplCreate.Empty, player.clubID, "Creating new record does not return id");

        return player.ClubID();
    }




}