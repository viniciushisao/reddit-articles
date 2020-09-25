package br.com.hisao.redditarticles.overview

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.hisao.redditarticles.db.ArticleDatabase
import br.com.hisao.redditarticles.getOrAwaitValue
import br.com.hisao.redditarticles.repository.RedditRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.junit.*
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(AndroidJUnit4::class)
class OverviewViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getArticleListViewModelLiveData() {
    }

    @Test
    fun getNavigateToArticleDetail() {

        val dataSource =
            ArticleDatabase.getInstance(ApplicationProvider.getApplicationContext()).dao
        val overviewViewModel = OverviewViewModel(RedditRepository(dataSource))

        val value = overviewViewModel.navigateToArticleDetail.getOrAwaitValue()

        assertThat(value, not(nullValue()))

    }

    @Test
    fun fetchArticleList() {
        val dataSource =
            ArticleDatabase.getInstance(ApplicationProvider.getApplicationContext()).dao
        val overviewViewModel = OverviewViewModel(RedditRepository(dataSource))

        overviewViewModel.fetchArticleList("kotlin");

        val value = overviewViewModel.articleListViewModelLiveData.getOrAwaitValue()

        assertThat(value, not(nullValue()))
    }

    @Test
    fun onArticleListClicked() {
    }

    @Test
    fun navigatedToArticleDetail() {
    }
}