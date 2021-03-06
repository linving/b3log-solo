/*
 * Copyright (c) 2009, 2010, 2011, 2012, 2013, B3log Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.b3log.solo;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import java.util.Locale;
import org.b3log.latke.Latkes;
import org.b3log.latke.service.LangPropsService;
import org.b3log.latke.service.LangPropsServiceImpl;
import org.b3log.solo.repository.ArchiveDateArticleRepository;
import org.b3log.solo.repository.ArchiveDateRepository;
import org.b3log.solo.repository.ArticleRepository;
import org.b3log.solo.repository.CommentRepository;
import org.b3log.solo.repository.LinkRepository;
import org.b3log.solo.repository.OptionRepository;
import org.b3log.solo.repository.PageRepository;
import org.b3log.solo.repository.PluginRepository;
import org.b3log.solo.repository.PreferenceRepository;
import org.b3log.solo.repository.StatisticRepository;
import org.b3log.solo.repository.TagArticleRepository;
import org.b3log.solo.repository.TagRepository;
import org.b3log.solo.repository.UserRepository;
import org.b3log.solo.repository.impl.ArchiveDateArticleRepositoryImpl;
import org.b3log.solo.repository.impl.ArchiveDateRepositoryImpl;
import org.b3log.solo.repository.impl.ArticleRepositoryImpl;
import org.b3log.solo.repository.impl.CommentRepositoryImpl;
import org.b3log.solo.repository.impl.LinkRepositoryImpl;
import org.b3log.solo.repository.impl.OptionRepositoryImpl;
import org.b3log.solo.repository.impl.PageRepositoryImpl;
import org.b3log.solo.repository.impl.PluginRepositoryImpl;
import org.b3log.solo.repository.impl.PreferenceRepositoryImpl;
import org.b3log.solo.repository.impl.StatisticRepositoryImpl;
import org.b3log.solo.repository.impl.TagArticleRepositoryImpl;
import org.b3log.solo.repository.impl.TagRepositoryImpl;
import org.b3log.solo.repository.impl.UserRepositoryImpl;
import org.b3log.solo.service.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Abstract test case.
 * 
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 1.0.0.7, Jul 8, 2013
 * @see #beforeClass() 
 * @see #afterClass() 
 */
public abstract class AbstractTestCase {

    /**
     * Local service test helper.
     */
    private final LocalServiceTestHelper localServiceTestHelper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    /**
     * User repository.
     */
    private UserRepository userRepository;

    /**
     * Link repository.
     */
    private LinkRepository linkRepository;

    /**
     * Article repository.
     */
    private ArticleRepository articleRepository;

    /**
     * Tag repository.
     */
    private TagRepository tagRepository;

    /**
     * Tag-Article repository.
     */
    private TagArticleRepository tagArticleRepository;

    /**
     * Page repository.
     */
    private PageRepository pageRepository;

    /**
     * Comment repository.
     */
    private CommentRepository commentRepository;

    /**
     * Archive date repository.
     */
    private ArchiveDateRepository archiveDateRepository;

    /**
     * Archive date article repository.
     */
    private ArchiveDateArticleRepository archiveDateArticleRepository;

    /**
     * Plugin repository.
     */
    private PluginRepository pluginRepository;

    /**
     * Preference repository.
     */
    private PreferenceRepository preferenceRepository;

    /**
     * Statistic repository.
     */
    private StatisticRepository statisticRepository;

    /**
     * Option repository.
     */
    private OptionRepository optionRepository;

    /**
     * Language service.
     */
    private LangPropsService langPropsService;
    
    /**
     * Plugin management service.
     */
    private PluginMgmtService pluginMgmtService;
    
    /**
     * Initialization service.
     */
    private InitService initService;

    /**
     * User management service.
     */
    private UserMgmtService userMgmtService;

    /**
     * User query service.
     */
    private UserQueryService userQueryService;

    /**
     * Article management service.
     */
    private ArticleMgmtService articleMgmtService;

    /**
     * Article query service.
     */
    private ArticleQueryService articleQueryService;

    /**
     * Page management service.
     */
    private PageMgmtService pageMgmtService;

    /**
     * Page query service.
     */
    private PageQueryService pageQueryService;

    /**
     * Link management service.
     */
    private LinkMgmtService linkMgmtService;

    /**
     * Link query service.
     */
    private LinkQueryService linkQueryService;

    /**
     * Preference management service.
     */
    private PreferenceMgmtService preferenceMgmtService;

    /**
     * Preference query service.
     */
    private PreferenceQueryService preferenceQueryService;

    /**
     * Tag query service.
     */
    private TagQueryService tagQueryService;

    /**
     * Tag management service.
     */
    private TagMgmtService tagMgmtService;

    /**
     * Comment query service.
     */
    private CommentQueryService commentQueryService;

    /**
     * Comment management service.
     */
    private CommentMgmtService commentMgmtService;

    /**
     * Archive date query service.
     */
    private ArchiveDateQueryService archiveDateQueryService;

    /**
     * Option management service.
     */
    private OptionMgmtService optionMgmtService;

    /**
     * Option query service.
     */
    private OptionQueryService optionQueryService;

    /**
     * Permalink query service.
     */
    private PermalinkQueryService permalinkQueryService;

    /**
     * Statistic management service.
     */
    private StatisticMgmtService statisticMgmtService;

    /**
     * Statistic query service.
     */
    private StatisticQueryService statisticQueryService;

    /**
     * Before class.
     * 
     * <ol>
     *   <li>Sets up GAE unit test runtime environment</li>
     *   <li>Initializes Latke runtime</li>
     *   <li>Instantiates repositories</li>
     * </ol>
     */
    @BeforeClass
    public void beforeClass() {
        localServiceTestHelper.setUp();

        Latkes.initRuntimeEnv();
        Latkes.setLocale(Locale.SIMPLIFIED_CHINESE);
        
        // Repositories
        userRepository = new UserRepositoryImpl();
        linkRepository = new LinkRepositoryImpl();
        articleRepository = new ArticleRepositoryImpl();
        tagArticleRepository = new TagArticleRepositoryImpl();
        tagRepository = new TagRepositoryImpl();
        ((TagRepositoryImpl)tagRepository).setTagArticleRepository(tagArticleRepository);
        pageRepository = new PageRepositoryImpl();
        commentRepository = new CommentRepositoryImpl();
        ((CommentRepositoryImpl)commentRepository).setArticleRepository(articleRepository);
        archiveDateRepository = new ArchiveDateRepositoryImpl();
        archiveDateArticleRepository = new ArchiveDateArticleRepositoryImpl();
        pluginRepository = new PluginRepositoryImpl();
        preferenceRepository = new PreferenceRepositoryImpl();
        statisticRepository = new StatisticRepositoryImpl();
        optionRepository = new OptionRepositoryImpl();

        // Services
        langPropsService = new LangPropsServiceImpl();
        
        pluginMgmtService = new PluginMgmtService();
        pluginMgmtService.setPluginRepository(pluginRepository);
        pluginMgmtService.setLangPropsService(langPropsService);
        
        preferenceQueryService = new PreferenceQueryService();
        preferenceQueryService.setPreferenceRepository(preferenceRepository);

        initService = new InitService();
        initService.setArchiveDateArticleRepository(archiveDateArticleRepository);
        initService.setArchiveDateRepository(archiveDateRepository);
        initService.setUserRepository(userRepository);
        initService.setArticleRepository(articleRepository);
        initService.setPreferenceRepository(preferenceRepository);
        initService.setStatisticRepository(statisticRepository);
        initService.setTagArticleRepository(tagArticleRepository);
        initService.setTagRepository(tagRepository);
        initService.setCommentRepository(commentRepository);
        initService.setLangPropsService(langPropsService);

        userMgmtService = new UserMgmtService();
        userMgmtService.setUserRepository(userRepository);
        userMgmtService.setLangPropsService(langPropsService);

        userQueryService = new UserQueryService();
        userQueryService.setUserMgmtService(userMgmtService);
        userQueryService.setUserRepository(userRepository);

        permalinkQueryService = new PermalinkQueryService();
        permalinkQueryService.setArticleRepository(articleRepository);
        permalinkQueryService.setPageRepository(pageRepository);

        statisticMgmtService = new StatisticMgmtService();
        statisticMgmtService.setStatisticRepository(statisticRepository);
        statisticMgmtService.setLangPropsService(langPropsService);

        statisticQueryService = new StatisticQueryService();
        statisticQueryService.setStatisticRepository(statisticRepository);

        tagQueryService = new TagQueryService();
        tagQueryService.setTagRepository(tagRepository);

        tagMgmtService = new TagMgmtService();
        tagMgmtService.setTagQueryService(tagQueryService);
        tagMgmtService.setTagRepository(tagRepository);

        articleQueryService = new ArticleQueryService();
        articleQueryService.setArchiveDateArticleRepository(archiveDateArticleRepository);
        articleQueryService.setArticleRepository(articleRepository);
        articleQueryService.setUserRepository(userRepository);
        articleQueryService.setPreferenceQueryService(preferenceQueryService);
        articleQueryService.setStatisticQueryService(statisticQueryService);
        articleQueryService.setTagArticleRepository(tagArticleRepository);
        articleQueryService.setTagRepository(tagRepository);

        articleMgmtService = new ArticleMgmtService();
        articleMgmtService.setArchiveDateArticleRepository(archiveDateArticleRepository);
        articleMgmtService.setArchiveDateRepository(archiveDateRepository);
        articleMgmtService.setArticleRepository(articleRepository);
        articleMgmtService.setPermalinkQueryService(permalinkQueryService);
        articleMgmtService.setArticleQueryService(articleQueryService);
        articleMgmtService.setUserRepository(userRepository);
        articleMgmtService.setArticleQueryService(articleQueryService);
        articleMgmtService.setPreferenceQueryService(preferenceQueryService);
        articleMgmtService.setStatisticMgmtService(statisticMgmtService);
        articleMgmtService.setStatisticQueryService(statisticQueryService);
        articleMgmtService.setTagArticleRepository(tagArticleRepository);
        articleMgmtService.setTagRepository(tagRepository);
        articleMgmtService.setTagMgmtService(tagMgmtService);
        articleMgmtService.setCommentRepository(commentRepository);
        articleMgmtService.setLangPropsService(langPropsService);

        pageMgmtService = new PageMgmtService();
        pageMgmtService.setPermalinkQueryService(permalinkQueryService);
        pageMgmtService.setPageRepository(pageRepository);
        pageMgmtService.setPreferenceQueryService(preferenceQueryService);
        pageMgmtService.setStatisticQueryService(statisticQueryService);
        pageMgmtService.setStatisticMgmtService(statisticMgmtService);
        pageMgmtService.setCommentRepository(commentRepository);
        pageMgmtService.setLangPropsService(langPropsService);

        pageQueryService = new PageQueryService();
        pageQueryService.setPageRepository(pageRepository);

        linkMgmtService = new LinkMgmtService();
        linkMgmtService.setLinkRepository(linkRepository);

        linkQueryService = new LinkQueryService();
        linkQueryService.setLinkRepository(linkRepository);

        preferenceMgmtService = new PreferenceMgmtService();
        preferenceMgmtService.setPreferenceRepository(preferenceRepository);
        preferenceMgmtService.setLangPropsService(langPropsService);

        commentQueryService = new CommentQueryService();
        commentQueryService.setArticleRepository(articleRepository);
        commentQueryService.setPageRepository(pageRepository);
        commentQueryService.setCommentRepository(commentRepository);

        commentMgmtService = new CommentMgmtService();
        commentMgmtService.setArticleRepository(articleRepository);
        commentMgmtService.setArticleMgmtService(articleMgmtService);
        commentMgmtService.setPageRepository(pageRepository);
        commentMgmtService.setPreferenceQueryService(preferenceQueryService);
        commentMgmtService.setStatisticMgmtService(statisticMgmtService);
        commentMgmtService.setCommentRepository(commentRepository);
        commentMgmtService.setLangPropsService(langPropsService);

        archiveDateQueryService = new ArchiveDateQueryService();
        archiveDateQueryService.setArchiveDateRepository(archiveDateRepository);

        optionMgmtService = new OptionMgmtService();
        optionMgmtService.setOptionRepository(optionRepository);

        optionQueryService = new OptionQueryService();
        optionQueryService.setOptionRepository(optionRepository);
    }

    /**
     * After class.
     * 
     * <ol>
     *   <li>Tears down GAE unit test runtime environment</li>
     *   <li>Shutdowns Latke runtime</li>
     * </ol>
     */
    @AfterClass
    public void afterClass() {
        // XXX: NPE, localServiceTestHelper.tearDown();

        Latkes.shutdown();
    }

    /**
     * Gets user repository.
     * 
     * @return user repository
     */
    public UserRepository getUserRepository() {
        return userRepository;
    }

    /**
     * Gets link repository.
     * 
     * @return link repository
     */
    public LinkRepository getLinkRepository() {
        return linkRepository;
    }

    /**
     * Gets article repository.
     * 
     * @return article repository
     */
    public ArticleRepository getArticleRepository() {
        return articleRepository;
    }

    /**
     * Gets tag repository.
     * 
     * @return tag repository
     */
    public TagRepository getTagRepository() {
        return tagRepository;
    }

    /**
     * Gets tag-article repository.
     * 
     * @return tag-article repository
     */
    public TagArticleRepository getTagArticleRepository() {
        return tagArticleRepository;
    }

    /**
     * Gets page repository.
     * 
     * @return page repository
     */
    public PageRepository getPageRepository() {
        return pageRepository;
    }

    /**
     * Gets comment repository.
     * 
     * @return comment repository
     */
    public CommentRepository getCommentRepository() {
        return commentRepository;
    }

    /**
     * Gets archive date repository.
     * 
     * @return archive date repository
     */
    public ArchiveDateRepository getArchiveDateRepository() {
        return archiveDateRepository;
    }

    /**
     * Archive date article repository.
     * 
     * @return archive date article repository
     */
    public ArchiveDateArticleRepository getArchiveDateArticleRepository() {
        return archiveDateArticleRepository;
    }

    /**
     * Gets plugin repository.
     * 
     * @return plugin repository
     */
    public PluginRepository getPluginRepository() {
        return pluginRepository;
    }

    /**
     * Gets preference repository.
     * 
     * @return preference repository
     */
    public PreferenceRepository getPreferenceRepository() {
        return preferenceRepository;
    }

    /**
     * Gets statistic repository.
     * 
     * @return statistic repository
     */
    public StatisticRepository getStatisticRepository() {
        return statisticRepository;
    }

    /**
     * Gets option repository.
     * 
     * @return option repository
     */
    public OptionRepository getOptionRepository() {
        return optionRepository;
    }

    /**
     * Gets initialization service.
     * 
     * @return initialization service
     */
    public InitService getInitService() {
        return initService;
    }

    /**
     * Gets user management service.
     * 
     * @return user management service
     */
    public UserMgmtService getUserMgmtService() {
        return userMgmtService;
    }

    /**
     * Gets user query service.
     * 
     * @return user query service
     */
    public UserQueryService getUserQueryService() {
        return userQueryService;
    }

    /**
     * Gets article management service.
     * 
     * @return article management service
     */
    public ArticleMgmtService getArticleMgmtService() {
        return articleMgmtService;
    }

    /**
     * Gets article query service.
     * 
     * @return article query service
     */
    public ArticleQueryService getArticleQueryService() {
        return articleQueryService;
    }

    /**
     * Gets page management service.
     * 
     * @return page management service
     */
    public PageMgmtService getPageMgmtService() {
        return pageMgmtService;
    }

    /**
     * Gets page query service.
     * 
     * @return page query service
     */
    public PageQueryService getPageQueryService() {
        return pageQueryService;
    }

    /**
     * Gets link management service.
     * 
     * @return link management service
     */
    public LinkMgmtService getLinkMgmtService() {
        return linkMgmtService;
    }

    /**
     * Gets link query service.
     *
     * @return link query service 
     */
    public LinkQueryService getLinkQueryService() {
        return linkQueryService;
    }

    /**
     * Gets preference management service.
     * 
     * @return preference management service
     */
    public PreferenceMgmtService getPreferenceMgmtService() {
        return preferenceMgmtService;
    }

    /**
     * Gets preference query service.
     * 
     * @return preference query service
     */
    public PreferenceQueryService getPreferenceQueryService() {
        return preferenceQueryService;
    }

    /**
     * Gets tag query service.
     * 
     * @return tag query service
     */
    public TagQueryService getTagQueryService() {
        return tagQueryService;
    }

    /**
     * Gets tag management service.
     * 
     * @return tag management service
     */
    public TagMgmtService getTagMgmtService() {
        return tagMgmtService;
    }

    /**
     * Gets comment query service.
     * 
     * @return comment query service
     */
    public CommentQueryService getCommentQueryService() {
        return commentQueryService;
    }

    /**
     * Gets comment management service.
     * 
     * @return comment management service
     */
    public CommentMgmtService getCommentMgmtService() {
        return commentMgmtService;
    }

    /**
     * Gets archive date query service.
     * 
     * @return archive date query service
     */
    public ArchiveDateQueryService getArchiveDateQueryService() {
        return archiveDateQueryService;
    }

    /**
     * Gets option management service.
     * 
     * @return option management service
     */
    public OptionMgmtService getOptionMgmtService() {
        return optionMgmtService;
    }

    /**
     * Gets option query service.
     * 
     * @return option query service
     */
    public OptionQueryService getOptionQueryService() {
        return optionQueryService;
    }
}
